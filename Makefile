define JSON_TODO
curl -X 'POST' \
  'http://localhost:8080/todo' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "description": "string",
  "done": true,
  "dueDate": {
    "due": "2021-05-07",
    "start": "2021-05-07"
  },
  "title": "string"
}'
endef
export JSON_TODO

# Docker
.PHONY: docker
docker:
	@docker-compose -f docker/docker-compose.yaml \
		-p axon up

# Tools
todo:
	@echo $$JSON_TODO | bash

list:
	@curl -X 'GET' 'http://localhost:8080/todo' -H 'accept: */*' | jq .

find:
	@curl -X 'GET' 'http://localhost:8080/todo/$(ID)' -H 'accept: */*' | jq .

done:
	@curl -X 'PUT' 'http://localhost:8080/todo/$(ID)/done' -H 'accept: */*' | jq .
