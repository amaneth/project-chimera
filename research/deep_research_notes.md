# Project Chimera - Deep Research Notes

## Reading 1: The Trillion Dollar AI Software Development Stack

- AI development is moving toward Plan → Code → Review.
- Specs guide implementation and keep human/AI intent aligned.
- `.cursor/rules` teach agents how to behave within a project.
- AI agents need project context, architecture rules, tests, and review loops.
- Background agents require automated tests to verify correctness.
- Source control should track intent, decisions, tests, and implementation history.
- Code sandboxes improve safety and repeatability.

### Key Takeaways
- Chimera should adopt strict spec-driven development.
- `specs/` should be the source of truth.
- AI agents must check specs before coding.
- Tests should define expected behavior before implementation.
- The repo should be engineered as context for future AI agents.

## Reading 2: OpenClaw's AI Assistants Are Now Building Their Own Social Network

- OpenClaw is an open-source personal AI assistant.
- Moltbook is a social network where AI agents interact.
- Agents can post, comment, share information, and use topic forums.
- Agents can interact through a skill system.
- Skills are downloadable instruction files for repeated actions.
- Agents can periodically fetch updates.
- Fetching and following internet instructions creates security risks.
- Prompt injection remains an unsolved problem.
- OpenClaw is safer in controlled environments than in mainstream use.

### Key Takeaways
- Chimera belongs to the shift from passive chatbots to action-taking agents.
- Chimera agents need skills, permissions, memory, and communication channels.
- Agent-to-agent communication must be treated as untrusted input.
- External agent messages should pass through safety filters and human review.
- Human-in-the-loop review is needed for risky public actions.
- Chimera should not blindly fetch or follow instructions from other agents or the internet.

## Reading 3: OpenClaw and Moltbook: Social Media for Bots

- OpenClaw agents can run locally and integrate with apps like WhatsApp and Discord.
- Skills contain instructions, scripts, and reference files.
- Agentic AI can plan actions, call tools, and execute tasks with limited human oversight.
- Moltbook lets bots create accounts, posts, comments, and submolts.
- Bots share automation tricks, security findings, and discussion threads.
- What feels new is the breadth of automation: planning, tool use, execution, and distribution under one system.

### Key Takeaways
- Chimera should define reusable runtime skills.
- Skills must be separate from MCP servers.
- Chimera agents need guardrails because they are public-facing.
- Chimera agents need identity, status, capability, refusal, and escalation behavior.


## Reading 4: Project Chimera SRS
- Project Chimera is an Autonomous Influencer Network.
- It is not a simple content scheduler.
- Agents perceive trends, reason about goals, create content, interact with users, remember context, and may transact.
- The core architecture is Planner -> Worker -> Judge.
- The Planner breaks high-level goals into tasks.
- The Worker executes atomic tasks.
- The Judge reviews outputs, approves, rejects, or escalates them.
- MCP is the integration layer for external systems.
- Direct external API calls from the agent core should be avoided.
- Human-in-the-loop review governs risky or uncertain actions.
- SOUL.md defines each agent's persona.
- Redis handles queues and short-term memory.
- Weaviate handles long-term semantic memory.
- PostgreSQL handles transactional data.
- OCC with state_version prevents stale commits.
- Java 21 records should be used for immutable DTOs.
- Java 21 virtual threads support scalable worker execution.
- Financial actions require a CFO Judge and budget limits.

### Key Takeaways
 - Chimera should use a Hieararchial Swarm architecture with Planner, Worker, and Judge roles.
 - The main loop should be Planner -> Worker -> Judge.
 - MCP should be the boundary between the agent core and external systems.
 - Specs, API contracts, schemas and tests should be created before implementation.
 - Define clear boundaries and responsibilities for the Planner, Worker, and Judge roles to prevent feature creep and improve maintainability.
 - Ensure the architecture supports auditability: logs, decisions, approvals, and rejected actions should be traceable.
 - Build strong failure handling for automated tasks, including retries, rollback, and safe abort behavior.
 - Define security guardrails for public-facing agents, including permission scopes, rate limits, and safe defaults


## Analysis Question 1: How does Project Chimera fit into the Agent Social Network?

Project Chimera fits into the Agent Social Network as a fleet of autonomous influencer agents that can interact with humans and other AI agents.

OpenClaw and Moltbook show that agents can create accounts, post, comment, share updates, and organize into communities. Chimera extends this idea into branded influencer agents that monitor trends, create content, collaborate, and manage public identity.

A Chimera agent could publish its niche, availability, persona, campaign goals, collaboration interests, and trust level to an agent network.

However, Chimera should be a governed participant, not a blind follower. Agent-to-agent messages must be treated as untrusted input and passed through filtering, Judge review, and HITL when needed.


## Analysis Question 2: What social protocols might Chimera need to communicate with other agents?

Chimera agents need formal social protocols so agent-to-agent communication is safe, predictable, and auditable.

### Identity and Disclosure Protocol
- Identifies the agent, owner, niche, persona, and AI disclosure status.
- Helps prevent impersonation and supports transparency.

### Capability Protocol
- Lists supported skills, MCP tools, platforms, languages, and restricted actions.
- Prevents agents from requesting unsupported or unsafe work.

### Availability and Status Protocol
- Publishes whether the agent is available, busy, offline, budget-limited, or awaiting human review.
- Helps other agents know when collaboration is possible.

### Collaboration Request Protocol
- Defines requester, target agent, requested action, campaign goal, expected output, deadline, and risk level.
- Turns vague agent messages into structured task proposals.

### Trust and Reputation Protocol
- Tracks verified owner, trust score, previous interactions, and safety flags.
- Helps the Judge decide whether incoming requests are safe.

### Content Exchange Protocol
- Shares content body, media URLs, source references, confidence score, disclosure level, and reuse permissions.
- Preserves provenance before content is published or reused.

### Approval and Escalation Protocol
- Communicates approved, rejected, needs revision, awaiting HITL, or blocked by policy.
- Keeps agent collaboration aligned with the Planner -> Worker -> Judge workflow.

### Refusal Protocol
- Gives clear reasons for rejecting requests, such as unsafe content, unsupported capability, insufficient trust, sensitive topic, or budget exceeded.
- Makes boundaries machine-readable and consistent.


## Final Research Takeaway

Project Chimera should be designed as governed agentic infrastructure, not just content automation.

The strongest direction is:

- Spec-driven development
- Planner -> Worker -> Judge swarm architecture
- MCP-first external integrations
- Reusable runtime skills
- Human-in-the-loop safety
- Agent identity and social protocols
- Prompt-injection defense
- Java 21 immutable DTOs and virtual threads
- Tests before implementation
- Governance before autonomy