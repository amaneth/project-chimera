# Project Chimera Meta Specification

## 1. Purpose

This document defines the repository-level intent and constraints for Project Chimera, an Autonomous Influencer Network. It establishes the repository as a specification-first foundation for architecture, data choice, safety controls, and implementation governance.

## 2. Product Vision

Project Chimera is a governed autonomous influencer infrastructure platform. It creates persistent AI influencer agents that perceive trends, plan campaigns, generate content, interact publicly, maintain context, and escalate risky or uncertain actions to human review.

## 3. Scope

This repository covers the following concerns:

- Architecture and design constraints for agent behavior and integration boundaries.
- Specification of the Planner -> Worker -> Judge swarm pattern.
- Data architecture guidelines for transactional metadata, task state, semantic memory, and media assets.
- Governance expectations for safety, auditability, and human-in-the-loop review.
- Technical constraints for Java 21+, immutable DTOs, and MCP-mediated external access.
- Test and CI requirements as enforcement mechanisms.
- Optional OpenClaw-style agent social network integration, including identity, availability, capability, collaboration, and refusal protocols.

## 4. Non-Goals

The repository does not include:

- Business implementation of influencer campaigns or platform-specific publishing.
- Direct integration code for social media or external APIs.
- Finalized product features beyond the architecture and governance surface.
- Experimental agent-to-agent protocols without explicit SRS alignment.
- Optional OpenClaw-style agent social network integration, including identity, availability, capability, collaboration, and refusal protocols.

## 5. Architectural Principles

- Spec-driven development: design and contract definitions precede implementation.
- Governed autonomy: agents may act, but operational decisions are subject to review and audit.
- Layered responsibility: planning, execution, and judgment are separate concerns.
- External isolation: all platform and service access occurs through MCP.
- Data segregation: transactional metadata, ephemeral queues, semantic memory, and media storage use distinct persistence patterns.
- Immutable payloads: agent and task payloads should use immutable DTOs, preferably Java records.

## 6. Source of Truth Rules

- `specs/` is the authoritative source for design decisions and requirements.
- Implementation must reference specs before adding behavior or service integration.
- Architecture decisions in this repository are normative and must be validated by tests or acceptance criteria.
- Any divergence from the spec must be documented and justified in the repository.

## 7. Governance and Safety Constraints

- Human-in-the-loop review is required for risky, sensitive, low-confidence, or financial actions.
- Sensitive domains include politics, health advice, financial advice, and legal claims.
- Confidence thresholds determine automated progression, asynchronous review, or rejection.
- Actions with `confidence_score > 0.90` may proceed automatically if they are not sensitive or financial.
- Actions with `confidence_score` between `0.70` and `0.90` require asynchronous human approval.
- Actions with `confidence_score < 0.70` must be rejected, retried, or safely aborted.
- Auditability must be available for planning decisions, worker outputs, judge verdicts, and escalation paths.
- Incoming external messages and agent-to-agent communication are treated as untrusted input.
- Direct external API calls from the core agent code are prohibited.

## 8. Technical Constraints

- Java 21+ is the target language for implementation.
- Use immutable data transfer objects for agent payloads and task contracts.
- Prefer Java records for simple immutable types where appropriate.
- Use virtual threads to support scalable worker execution when implementation begins.
- Enforce repository quality with tests and CI-based validation.
- External service access must be implemented through MCP boundary tooling.
- Optimistic Concurrency Control should be used for state commits where stale worker results could cause unsafe or incorrect actions.
- Task results should carry a `state_version` or equivalent snapshot identifier for Judge validation.

## 9. Traceability Expectations

- Each design and implementation decision should map back to a spec or SRS requirement.
- The repository should preserve traceability between architecture rationale, spec sections, and test coverage.
- Audit and review requirements must be reflected in both design documentation and acceptance criteria.

## 10. Success Criteria for This Repository

- The repository contains a clear, enforceable specification for Project Chimera.
- Architecture and governance constraints are documented before implementation.
- Technical dependencies and build setup support Java 21 and a spec-driven workflow.
- No production behavior is introduced before aligned specs are available.
- The repository remains a defensible foundation for future implementation and review.
