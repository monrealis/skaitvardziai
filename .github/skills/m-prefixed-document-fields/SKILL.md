---
name: m-prefixed-document-fields
description: Review structured documents and ensure every field name starts with lowercase m. Use when auditing or correcting field names in JSON, YAML, XML, tables, schemas, or similar document formats.
---

# Review document field names

Review the documents in the requested scope and ensure every field identifier starts with lowercase `m`.

## What counts as a field

- Treat keys, property names, column headers, and declared schema fields as fields.
- Apply the rule to field identifiers only. Do not change prose, headings, labels, values, example text, or filenames just because they do not start with `m`.
- Preserve the document format, field order, and existing naming style.

## Procedure

1. Identify the documents in scope and inspect enough surrounding context to distinguish field identifiers from ordinary text.
2. Find every field name that does not start with lowercase `m`.
3. Rename each such field by adding `m` to the beginning of its existing name. Do not alter names that already start with `m`.
4. Update references to renamed fields within the in-scope documents, such as schema declarations, examples, and field paths, so they remain consistent. Do not change unrelated identifiers or external interfaces without authorization.
5. Check the edited documents for formatting validity and confirm that every field name starts with lowercase `m`.
6. Report the documents reviewed, the field names changed, and any ambiguous fields that could not be safely identified or updated.

If the intended scope or whether a particular identifier is a field is unclear, ask before making potentially breaking changes.
