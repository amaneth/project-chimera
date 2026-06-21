# skill_budget_check

## Purpose

`skill_budget_check` evaluates whether a proposed action is permitted under campaign and tenant budget constraints.
It supports Project Chimera’s financial governance path and helps determine whether a CFO Judge review is required.

## When to Use

Use this skill before any cost-incurring action, including media generation, publishing, or buying services.
It should run after task planning and before any action that would consume budget.

## Inputs

- `tenant_id` (string): Tenant owning the campaign.
- `agent_id` (string): Agent proposing the action.
- `campaign_id` (string): Campaign context.
- `action_type` (string): Type of action being evaluated.
- `estimated_cost` (number): Estimated cost of the action.
- `currency` (string): Currency code for the estimate.
- `budget_period` (string): Budget period identifier (e.g. MONTHLY, QUARTERLY).

## Outputs

- `check_id` (string): Unique identifier for the budget check.
- `decision` (string): Decision result, such as `APPROVE` or `REJECT`.
- `remaining_budget` (number): Remaining budget after accounting for the estimated cost.
- `estimated_cost` (number): Echoed estimated cost.
- `currency` (string): Currency code.
- `reason` (string): Explanation for the decision.
- `requires_cfo_review` (boolean): Whether CFO review is required.
- `created_at` (string): ISO timestamp when the check was created.

## Required MCP Tools / Resources

- Approved budget or finance lookup tools for campaign and tenant budget state.
- Any MCP tool used to validate cost estimates or currency normalization.

## Validation Rules

- Validate all input fields are present and properly typed.
- Confirm `tenant_id`, `agent_id`, and `campaign_id` are non-empty.
- Ensure `estimated_cost` is non-negative and `currency` is valid.
- Check `budget_period` against known budget cycles.
- Do not execute transactions or access secrets.
- Treat budget state inputs as untrusted and verify them through MCP resources.

## Failure Modes

- Budget state retrieval fails or returns inconsistent values.
- `estimated_cost` is malformed or missing.
- `currency` is unsupported.
- `budget_period` is invalid.
- Budget lookup tools are unavailable or time out.

## Security / Governance Rules

- Do not execute payments or financial transactions.
- Do not access or expose secrets.
- If the estimated cost exceeds available budget, return `REJECT`.
- If the action is financial or high-cost, set `requires_cfo_review` to true.
- Preserve audit metadata for the decision and any budget-affecting checks.
- Ensure the output is suitable for Judge and CFO review rather than action execution.

## Example Input JSON

```json
{
  "tenant_id": "tenant-001",
  "agent_id": "chimera-influencer-001",
  "campaign_id": "campaign-xyz789",
  "action_type": "media_generation",
  "estimated_cost": 120.50,
  "currency": "USD",
  "budget_period": "MONTHLY"
}
```

## Example Output JSON

```json
{
  "check_id": "budget-check-550e8400-e29b-41d4-a716-446655440001",
  "decision": "APPROVE",
  "remaining_budget": 879.50,
  "estimated_cost": 120.50,
  "currency": "USD",
  "reason": "Within campaign monthly budget",
  "requires_cfo_review": false,
  "created_at": "2026-06-21T12:45:00Z"
}
```

## Acceptance Criteria

- The skill contract includes the required input and output fields.
- The skill does not execute transactions or access secrets.
- Budget-overrun conditions return `REJECT`.
- High-cost or financial actions set `requires_cfo_review` to true.
- The output is suitable for Judge and CFO review.
