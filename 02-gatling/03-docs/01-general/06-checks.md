# Checks

https://gatling.io/docs/current/http/http_check/

Gatling is able to analyze this response with Checks

- A check is a response processor that captures some part of it and verifies that it meets some given condition(s).
- Checks can be used to capture some elements and store them into the Session so that they can be reused later.
- Checks are performed on a request with the check method. For example, on a HTTP request
