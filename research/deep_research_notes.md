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
