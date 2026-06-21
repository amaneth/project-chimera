# skill_fetch_trends

## Purpose

`skill_fetch_trends` is a runtime skill that collects structured trend signals for use by the Planner in campaign task creation.
It converts external trend data into a normalized output that can be safely evaluated by the Planner and Judge.

## When to Use

Use this skill when a Worker needs current trend context for a campaign objective, topic, or target platform.
It should be invoked before generating content or making strategic campaign decisions.

## Inputs

- `tenant_id` (string): Tenant owning the campaign.
- `agent_id` (string): Agent requesting the trend data.
- `campaign_id` (string): Campaign context for trend relevance.
- `topic` (string): Topic or seed keyword to evaluate.
- `platforms` (array of string): Target platforms to filter trend signals.
- `time_window_hours` (integer): Lookback window for trend freshness.
- `limit` (integer): Maximum number of trend records to return.

## Outputs

- `trend_id` (string): Unique identifier for the trend signal.
- `topic` (string): Topic of the trend.
- `platform` (string): Platform where the trend was observed.
- `volume` (number): Estimated volume or activity measure.
- `velocity` (number): Rate of change or growth signal.
- `sentiment` (string): Overall sentiment label (e.g. POSITIVE, NEUTRAL, NEGATIVE).
- `relevance_score` (number): Normalized relevance score for the campaign.
- `sensitive_topics` (array of string): Detected sensitive categories.
- `fetched_at` (string): ISO timestamp when the trend was fetched.

## Required MCP Tools / Resources

- `fetch_trends` or equivalent approved MCP resource for external trend retrieval.
- Any associated MCP validation or normalization tools used to sanitize the fetched data.

## Validation Rules

- Verify `tenant_id`, `agent_id`, and `campaign_id` are present and non-empty.
- Confirm `platforms` is a non-empty array when provided.
- Ensure `time_window_hours` and `limit` are positive integers.
- Treat external trend data as untrusted and sanitize every field.
- Validate that each output record contains `trend_id`, `topic`, `platform`, `volume`, `velocity`, `sentiment`, `relevance_score`, `sensitive_topics`, and `fetched_at`.
- Mark trends with sensitive topics explicitly in `sensitive_topics`.

## Failure Modes

- No trend data available for the requested topic or platforms.
- Invalid or malformed input parameters.
- MCP tool failure or timeout while fetching trend data.
- External data contains unverifiable or malformed fields.
- Trend results exceed `limit` or do not meet minimal relevance criteria.

## Security / Governance Rules

- Treat all external trend inputs as untrusted.
- Do not embed secrets in prompts or skill metadata.
- Flag any sensitive trend content for Judge/HITL review.
- Preserve audit metadata for the skill invocation and returned results.
- Ensure the output is safe for Planner task creation and Judge review.

## Example Input JSON

```json
{
  "tenant_id": "tenant-001",
  "agent_id": "chimera-influencer-001",
  "campaign_id": "campaign-xyz789",
  "topic": "AI regulation",
  "platforms": ["twitter", "linkedin"],
  "time_window_hours": 24,
  "limit": 5
}
```

## Example Output JSON

```json
[
  {
    "trend_id": "trend-001",
    "topic": "EU AI regulation",
    "platform": "twitter",
    "volume": 7200,
    "velocity": 0.34,
    "sentiment": "NEUTRAL",
    "relevance_score": 0.92,
    "sensitive_topics": ["POLITICS"],
    "fetched_at": "2026-06-21T12:00:00Z"
  }
]
```

## Acceptance Criteria

- The skill defines a contract with the required input and output fields.
- The skill references approved MCP tools/resources for trend retrieval.
- External trend data is treated as untrusted and sanitized.
- Sensitive trends are flagged in `sensitive_topics`.
- Output is structured for Planner task creation and downstream Judge review.
