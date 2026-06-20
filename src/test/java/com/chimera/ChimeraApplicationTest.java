package com.chimera;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChimeraApplicationTest {

    @Test
    void projectNameShouldIdentifyChimera() {
        assertThat(ChimeraApplication.projectName())
                .isEqualTo("Project Chimera");
    }
}
