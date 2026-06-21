# Project Chimera Tooling Strategy

## Purpose

This document separates developer MCP tools from runtime Chimera Agent skills and captures a tooling strategy for a Java 21, MCP-mediated Planner -> Worker -> Judge architecture.
It emphasizes that developer tooling is for build-time governance, while runtime agent skills are governed execution capabilities.

## Difference Between Developer MCP Tools and Runtime Skills

Developer MCP tools are build-time utilities used by human developers during repository construction, inspection, and review.
They are not automatically available to runtime Chimera agents.

Runtime skills are reusable capability packages used by Worker agents during execution. These skills must have explicit README-style contracts describing inputs, outputs, and behavior.

## Developer MCP Tooling Strategy

- Tenx MCP Sense
  - Use Tenx MCP Sense for development telemetry, trigger enforcement, and auditability during code changes.
  - It supports the mandatory pre-analysis and logging behavior required by `.github/copilot-instructions.md`.

- Git / GitHub tooling
  - Use Git for source control, branch history, and spec-aligned development.
  - Use GitHub tooling for PR review, issue tracking, and CI validation.

- Filesystem tooling
  - Use filesystem tools for safe inspection, structured edits, and repository maintenance.
  - This tooling is for developers only and must not be granted to runtime agents.

- Optional database/browser/API tooling
  - Reserve these tools for future development and debugging, such as local DB inspection or API contract validation.
  - Treat them as developer-only utilities, not runtime capabilities.

## MCP Tool Selection Table

| Tool / Server | Purpose | Development Use Case | Risk | Guardrail |
|---|---|---|---|---|
| Tenx MCP Sense | Development telemetry and trigger enforcement | Validate rule compliance and capture developer action logs | Confusing dev-only telemetry with runtime capability | Document as developer-only and keep runtime agent access separate |
| Git / GitHub | Source control, PR review, CI visibility | Track spec alignment, review tests, manage branches | Merging unreviewed or spec-misaligned changes | Require spec references in PRs and enforce code review |
| Filesystem tooling | Safe file reads and structured edits | Maintain docs, specs, and source files | Unintended repo changes or destructive edits | Restrict to explicit repository file operations and require manual confirmation for large edits |
| Local DB / browser tooling | Development environment inspection | Validate state, queues, and OCC behavior in dev | Treating local inspection as runtime permission | Keep tool usage in development contexts only |
| API tooling | Explore MCP tool contracts and integration points | Document expected external capability requirements | Mistaking direct external calls for allowed core behavior | Enforce that runtime only uses approved MCP tools and not direct APIs |

## Runtime Skills Strategy

Runtime skills are reusable capability packages with documented contracts. They are consumed by Worker agents to perform atomic tasks.

- Skills must define inputs, outputs, and constraints before implementation.
- Each skill should have a README-style contract that specifies allowed MCP tools, required data, and governance expectations.
- Runtime skills are not developer MCP tools and do not automatically inherit developer tooling permissions.

## Skill Selection Rationale

Initial critical skills should support trend-aware content generation and core governance paths.

- `skill_fetch_trends`
  - Purpose: retrieve trend signals and context needed for campaign planning.
  - Rationale: workers need trend context to generate relevant influencer content while obeying MCP-mediated external access rules.

- `skill_draft_social_post`
  - Purpose: generate draft social media content aligned to campaign goals, persona, and platform constraints.
  - Rationale: this is a primary Worker capability that produces deliverables for Judge validation.

- `skill_budget_check` (optional)
  - Purpose: validate campaign budget availability and enforce spending limits.
  - Rationale: supports financial governance and ensures budget constraints are checked before action execution.

## Security and Governance

- Apply least privilege: only grant runtime agents the skills and MCP tools they require.
- Preserve auditability: log developer actions and runtime skill invocations for trace history.
- Defend against prompt injection: treat external inputs as untrusted and validate them before use.
- Do not embed secrets in prompts or skill contracts.
- Require Judge review for sensitive, low-confidence, or stale-state actions.
- Forbid direct external API calls from core agent code; all runtime integration must go through MCP tools.

## Acceptance Criteria

- The document clearly separates developer MCP tools from runtime Chimera Agent skills.
- Developer MCP tools are described as developer-only and not automatically available to runtime agents.
- Runtime skills are described as reusable capability packages with README-style contracts.
- The tooling strategy references the spec-driven architecture and the governance rules in `.cursor/rules`.
- The table includes tool purpose, development use case, risk, and guardrail.
- `skill_fetch_trends` and `skill_draft_social_post` are selected, with `skill_budget_check` noted as optional.
- Security and governance controls are explicit and aligned with Project Chimera constraints.
