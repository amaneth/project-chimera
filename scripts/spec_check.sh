#!/usr/bin/env bash
set -euo pipefail

# Project Chimera spec-check
#
# Intent:
# Validate that the repository keeps the required spec-driven structure before
# AI agents or developers add implementation code.

REPO_ROOT=$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)
EXIT_CODE=0

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo "Checking Project Chimera repository structure..."
echo ""

# Function to check if a file exists
check_file() {
    local filepath=$1
    local description=$2
    if [ -f "$REPO_ROOT/$filepath" ]; then
        echo -e "${GREEN}✓${NC} $description"
        return 0
    else
        echo -e "${RED}✗${NC} $description (missing: $filepath)"
        EXIT_CODE=1
        return 1
    fi
}

# Function to check if a file exists with warning (not fatal)
check_file_optional() {
    local filepath=$1
    local description=$2
    if [ -f "$REPO_ROOT/$filepath" ]; then
        echo -e "${GREEN}✓${NC} $description"
        return 0
    else
        echo -e "${YELLOW}⚠${NC} $description (missing: $filepath)"
        return 1
    fi
}

# Function to check if a term exists in a file
check_term_in_file() {
    local filepath=$1
    local term=$2
    local description=$3
    if grep -q "$term" "$REPO_ROOT/$filepath" 2>/dev/null; then
        echo -e "${GREEN}✓${NC} $description (found: $term)"
        return 0
    else
        echo -e "${RED}✗${NC} $description (term not found: $term)"
        EXIT_CODE=1
        return 1
    fi
}

# Required spec files
echo "Spec Files:"
check_file "specs/_meta.md" "Meta specification"
check_file "specs/functional.md" "Functional specification"
check_file "specs/technical.md" "Technical specification"
check_file "specs/openclaw_integration.md" "OpenClaw integration specification"
echo ""

# Required configuration and rules
echo "Repository Configuration:"
check_file ".cursor/rules" "Cursor rules for AI agents"
check_file ".github/copilot-instructions.md" "GitHub Copilot instructions"
echo ""

# Required skill documentation
echo "Runtime Skills:"
check_file "skills/README.md" "Skills documentation"
echo ""

# Required test contracts
echo "Contract Tests:"
check_file "tests/com/chimera/TrendFetcherTest.java" "TrendFetcher contract test"
check_file "tests/com/chimera/SkillsInterfaceTest.java" "Skills interface contract test"
echo ""

# Build and automation files
echo "Build and Automation:"
check_file "Makefile" "Makefile"
check_file "pom.xml" "Maven POM"
echo ""

# Optional but recommended CI files
echo "Optional CI/CD:"
check_file_optional ".github/workflows/main.yml" "GitHub Actions main workflow"
echo ""

# Key terms in technical specification
echo "Technical Specification Content:"
check_term_in_file "specs/technical.md" "AgentTask" "Contains AgentTask model"
check_term_in_file "specs/technical.md" "WorkerResult" "Contains WorkerResult model"
check_term_in_file "specs/technical.md" "JudgeVerdict" "Contains JudgeVerdict model"
check_term_in_file "specs/technical.md" "state_version" "Contains state_version (OCC)"
check_term_in_file "specs/technical.md" "MCP" "References MCP boundary"
echo ""

if [ $EXIT_CODE -eq 0 ]; then
    echo -e "${GREEN}All required spec-driven structure checks passed!${NC}"
else
    echo -e "${RED}Some required files or terms are missing. Please review the output above.${NC}"
fi

exit $EXIT_CODE
