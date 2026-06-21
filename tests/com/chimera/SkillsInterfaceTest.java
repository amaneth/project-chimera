package com.chimera;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TDD contract test for future Chimera runtime skill interfaces.
 *
 * This test is intentionally expected to fail until the skill interfaces,
 * request records, response records, and governance exceptions are implemented.
 */
class SkillsInterfaceTest {

    @Test
    @DisplayName("DraftSocialPostRequest should carry required draft input fields")
    void shouldDefineDraftSocialPostRequestContract() {
        // Intentional TDD contract check for a future Java 21 record.
        assertThat(DraftSocialPostRequest.class).isNotNull();
        assertThat(DraftSocialPostRequest.class.isRecord()).isTrue();

        List<String> fieldNames = Arrays.stream(DraftSocialPostRequest.class.getRecordComponents())
                .map(rc -> rc.getName())
                .collect(Collectors.toList());

        assertThat(fieldNames).contains(
                "tenantId",
                "agentId",
                "campaignId",
                "taskId",
                "personaId",
                "campaignGoal",
                "platform",
                "disclosureLevel"
        );
    }

    @Test
    @DisplayName("DraftSocialPostResponse should carry required draft output fields")
    void shouldDefineDraftSocialPostResponseContract() {
        assertThat(DraftSocialPostResponse.class).isNotNull();
        assertThat(DraftSocialPostResponse.class.isRecord()).isTrue();

        List<String> fieldNames = Arrays.stream(DraftSocialPostResponse.class.getRecordComponents())
                .map(rc -> rc.getName())
                .collect(Collectors.toList());

        assertThat(fieldNames).contains(
                "draftId",
                "text",
                "hashtags",
                "confidenceScore",
                "riskLevel",
                "disclosureMetadata"
        );
    }

    @Test
    @DisplayName("BudgetCheckRequest and BudgetCheckResponse should define the budget skill contract")
    void shouldDefineBudgetCheckContracts() throws NoSuchMethodException {
        assertThat(BudgetCheckRequest.class).isNotNull();
        assertThat(BudgetCheckRequest.class.isRecord()).isTrue();
        assertThat(BudgetCheckResponse.class).isNotNull();
        assertThat(BudgetCheckResponse.class.isRecord()).isTrue();
        assertThat(BudgetCheckSkill.class).isNotNull();

        List<String> requestFieldNames = Arrays.stream(BudgetCheckRequest.class.getRecordComponents())
                .map(rc -> rc.getName())
                .collect(Collectors.toList());

        assertThat(requestFieldNames).containsExactlyInAnyOrder(
                "tenantId",
                "agentId",
                "campaignId",
                "actionType",
                "estimatedCost",
                "currency",
                "budgetPeriod"
        );

        Method checkMethod = BudgetCheckSkill.class.getMethod("checkBudget", BudgetCheckRequest.class);
        assertThat(checkMethod.getReturnType()).isEqualTo(BudgetCheckResponse.class);
    }

    @Test
    @DisplayName("BudgetCheckSkill should declare BudgetExceededException for overspending")
    void shouldRequireBudgetExceededExceptionForOverspending() throws NoSuchMethodException {
        assertThat(BudgetCheckSkill.class).isNotNull();

        Method checkMethod = BudgetCheckSkill.class.getMethod("checkBudget", BudgetCheckRequest.class);
        assertThat(Arrays.asList(checkMethod.getExceptionTypes()))
                .contains(BudgetExceededException.class);
    }

    @Test
    @DisplayName("ChimeraSkill and SkillExecutionContext should exist for runtime skill governance")
    void shouldDefineCommonSkillInterfaces() {
        assertThat(ChimeraSkill.class).isNotNull();
        assertThat(SkillExecutionContext.class).isNotNull();
    }
}