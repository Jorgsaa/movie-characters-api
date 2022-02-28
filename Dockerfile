FROM gradle:7.4-alpine
COPY . .
CMD gradle :bootRun
