# Project Chimera Makefile
#
# Intent:
# Standardize common development commands for Project Chimera.
# This repository follows Spec-Driven Development, so automation should support
# setup, testing, linting, and spec validation before implementation work.

.PHONY: help setup test lint clean verify spec-check

help:
	@echo "Project Chimera - Makefile Targets"
	@echo ""
	@echo "Usage: make [target]"
	@echo ""
	@echo "Targets:"
	@echo "  setup      - Resolve dependencies and build (mvn clean install -DskipTests)"
	@echo "  test       - Run JUnit 5 tests (includes intentional failing contract tests)"
	@echo "  lint       - Run code quality validation"
	@echo "  clean      - Clean build artifacts"
	@echo "  verify     - Run spec checks and lint validation"
	@echo "  spec-check - Verify spec files and repository structure exist"
	@echo "  help       - Show this help message"
	@echo ""

setup:
	@echo "Setting up Project Chimera..."
	mvn clean install -DskipTests

test:
	@echo "Running JUnit 5 tests..."
	mvn test

lint:
	@echo "Running code quality validation..."
	mvn -q -DskipTests validate

clean:
	@echo "Cleaning build artifacts..."
	mvn clean

verify: spec-check lint
	@echo "Verification complete (specs and lint validated)"

spec-check:
	@echo "Checking spec files and repository structure..."
	@if [ -f scripts/spec_check.sh ]; then \
		bash scripts/spec_check.sh; \
	else \
		echo "Warning: scripts/spec_check.sh not found"; \
		echo "Performing manual spec validation..."; \
		[ -f specs/_meta.md ] && echo "✓ specs/_meta.md found" || (echo "✗ specs/_meta.md missing" && exit 1); \
		[ -f specs/functional.md ] && echo "✓ specs/functional.md found" || (echo "✗ specs/functional.md missing" && exit 1); \
		[ -f specs/technical.md ] && echo "✓ specs/technical.md found" || (echo "✗ specs/technical.md missing" && exit 1); \
		[ -f specs/openclaw_integration.md ] && echo "✓ specs/openclaw_integration.md found" || (echo "✗ specs/openclaw_integration.md missing" && exit 1); \
		[ -f .cursor/rules ] && echo "✓ .cursor/rules found" || (echo "✗ .cursor/rules missing" && exit 1); \
		[ -f skills/README.md ] && echo "✓ skills/README.md found" || (echo "✗ skills/README.md missing" && exit 1); \
		[ -f tests/com/chimera/TrendFetcherTest.java ] && echo "✓ tests/com/chimera/TrendFetcherTest.java found" || (echo "✗ tests/com/chimera/TrendFetcherTest.java missing" && exit 1); \
		[ -f tests/com/chimera/SkillsInterfaceTest.java ] && echo "✓ tests/com/chimera/SkillsInterfaceTest.java found" || (echo "✗ tests/com/chimera/SkillsInterfaceTest.java missing" && exit 1); \
		echo "Manual spec check complete"; \
	fi
