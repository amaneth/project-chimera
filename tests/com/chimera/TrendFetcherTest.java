package com.chimera;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TDD contract test for the future trend fetching skill.
 *
 * This test is intentionally expected to fail until the corresponding
 * Java 21 records and skill interfaces are implemented from specs/technical.md
 * and skills/skill_fetch_trends/README.md.
 */
class TrendFetcherTest {

    @Test
    @DisplayName("FetchTrendsRequest should define the required trend fetch input contract")
    void shouldDefineFetchTrendsRequestInputContract() {
        // This is a contract test: the future DTO must be a Java 21 record.
        assertThat(FetchTrendsRequest.class).isNotNull();
        assertThat(FetchTrendsRequest.class.isRecord()).isTrue();

        List<String> fieldNames = Arrays.stream(FetchTrendsRequest.class.getRecordComponents())
                .map(rc -> rc.getName())
                .collect(Collectors.toList());

        assertThat(fieldNames).containsExactlyInAnyOrder(
                "tenantId",
                "agentId",
                "campaignId",
                "topic",
                "platforms",
                "timeWindowHours",
                "limit"
        );
    }

    @Test
    @DisplayName("TrendSignal should define the required trend output contract and preserve sensitive topics")
    void shouldDefineTrendSignalOutputContract() {
        assertThat(TrendSignal.class).isNotNull();
        assertThat(TrendSignal.class.isRecord()).isTrue();

        List<String> fieldNames = Arrays.stream(TrendSignal.class.getRecordComponents())
                .map(rc -> rc.getName())
                .collect(Collectors.toList());

        assertThat(fieldNames).containsExactlyInAnyOrder(
                "trendId",
                "topic",
                "platform",
                "volume",
                "velocity",
                "sentiment",
                "relevanceScore",
                "sensitiveTopics",
                "fetchedAt"
        );
    }

    @Test
    @DisplayName("TrendFetchResponse and TrendFetcherSkill should define the response and skill contract")
    void shouldDefineTrendFetchResponseAndSkillContract() throws NoSuchMethodException {
        assertThat(TrendFetchResponse.class).isNotNull();
        assertThat(TrendFetchResponse.class.isRecord()).isTrue();
        assertThat(TrendFetcherSkill.class).isNotNull();

        List<String> responseFieldNames = Arrays.stream(TrendFetchResponse.class.getRecordComponents())
                .map(rc -> rc.getName())
                .collect(Collectors.toList());

        assertThat(responseFieldNames).contains("trends");

        Method fetchMethod = TrendFetcherSkill.class.getMethod("fetchTrends", FetchTrendsRequest.class);
        assertThat(fetchMethod.getReturnType()).isEqualTo(TrendFetchResponse.class);
    }
}
