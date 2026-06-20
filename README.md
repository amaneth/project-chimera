# Project Chimera

## Project Overview

Project Chimera is a specification-first architecture foundation for an Autonomous Influencer Network. This repository captures the strategic design, functional contracts, and technical boundaries for a future Java 21-based agentic infrastructure challenge.

## Architecture Summary

Project Chimera is built around a Planner -> Worker -> Judge swarm architecture. The core design separates planning, execution, and review, and requires MCP-first external integrations so agent core logic never calls APIs directly.

## Repository Structure

- `research/`
  - `deep_research_notes.md` — research insights and agent network analysis.
  - `architecture_strategy.md` — architecture decision rationale.
  - `mcp_connection_log.md` — MCP integration notes.
- `specs/`
  - `_meta.md` — repository-level meta specification.
  - `functional.md` — functional requirements and user stories.
  - `technical.md` — technical contracts, schemas, and data modeling.
  - `openclaw_integration.md` — safe social network integration specification.
- `pom.xml` — Maven build configuration for Java 21.
- `src/main/java/com/chimera/ChimeraApplication.java` — minimal placeholder application class.
- `src/test/java/com/chimera/ChimeraApplicationTest.java` — minimal sanity test.

## Day 1 Deliverables

- Captured project intent and architecture decisions.
- Defined spec-driven functional and technical contracts.
- Established MCP-first integration guidance.
- Created a minimal Maven Java 21 project scaffold.

## Technology Foundation

- Java 21 target runtime
- Maven build and dependency management
- JUnit 5 and AssertJ for testing
- Jackson for JSON contract support
- PostgreSQL / Redis / Weaviate / Object storage as architectural references

## How to Run

```bash
mvn clean test
```

This repository is not yet a fully implemented system. The current code is a placeholder scaffold; the focus is on specs and architecture.

## Spec-Driven Development Rules

- Specs are the source of truth before implementation.
- No feature implementation should precede agreed functional and technical contracts.
- All external integrations must be mediated by MCP.
- Agent-to-agent and external inputs are treated as untrusted.
- The Planner -> Worker -> Judge pattern is the mandatory core workflow.

## AI-Assisted Engineering Note

This repository is designed to support future AI-assisted engineering while preserving governance. Specs, contract definitions, and test expectations are intentionally written before production behavior.

## Current Status

- Architecture and specification documents are established.
- Maven and Java 21 project scaffolding are in place.
- Tests are currently minimal sanity checks.
- Day 2 work will focus on TDD-driven feature tests and implementation aligned with the established specs.
