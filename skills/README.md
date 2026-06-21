# Project Chimera Runtime Skills

## Intent

This directory defines reusable runtime skills for Chimera Agents.
These skills capture capability contracts and execution expectations before implementation.

## What a Runtime Skill Is

A runtime skill is a reusable capability package that Worker agents invoke to perform an atomic task.
It is not implementation code; it is a contract describing the skill’s purpose, expected inputs, expected outputs, and governance constraints.

## How Skills Differ from MCP Servers

- Runtime skills are agent-facing capabilities within the Chimera runtime.
- MCP servers expose external tools, resources, or services and are accessed through MCP-mediated calls.
- Skills may call approved MCP tools, but they do so within the governed runtime workflow.
- Developer MCP tools and servers are not automatically available to runtime agents.

## How Skills Fit into Planner -> Worker -> Judge

- Planner creates tasks and selects the skills needed to complete them.
- Worker executes the task by invoking the runtime skill and, if required, approved MCP tools.
- Judge validates the skill output, checks confidence, policy, and `state_version`, and decides whether to approve, reject, or escalate to HITL.

## Contract-First Discipline

Skills must define input/output contracts before implementation.
Each skill README should document:
- required inputs and types
- expected outputs and structure
- permitted MCP tools and external dependencies
- risk and governance considerations

## Auditable Execution

Skill execution must be auditable.
The Worker must emit trace data, confidence scores, and any artifacts needed for review.
Audit records should be sufficient for Judge review and future inspection.

## Sensitive or Risky Outputs

Sensitive or risky skill outputs must be sent to Judge or HITL review.
If a skill produces content involving politics, health advice, finance, legal claims, or other high-risk domains, the Judge should escalate according to governance rules.

## Initial Skills

The first runtime skills for Project Chimera are:
- `skill_fetch_trends`
- `skill_draft_social_post`
- `skill_budget_check`

These skills support core influencer workflows while preserving the separation between runtime capabilities and developer tooling.
