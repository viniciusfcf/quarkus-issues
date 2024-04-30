# Review triage example

This example demonstrates how to a sentiment-analyzing AI service
with `quarkus-langchain4j`.

## Running the example

A prerequisite to running this example is to provide your OpenAI API key.

```
ollama run mistral:7b-instruct-q4_K_M
```

Then, simply run the project in Dev mode:

```
mvn quarkus:dev
```

## Using the example

Open your browser and navigate to http://localhost:8080. The application
acts as a bank's robot that accepts new reviews written by clients, analyzes
their sentiment and thinks up an appropriate response to the client. Simply
write something positive (`Your services are great.`) or negative (`You are
thieves!`) into the form and click Submit.

## Issue

if the input it's: `Im good`:

```
- method: POST
- url: http://localhost:11434/api/generate
- headers: [Accept: application/json], [Content-Type: application/json], [User-Agent: Resteasy Reactive Client], [content-length: 1450]
- body: {"model":"mistral:7b-instruct-q4_K_M","prompt":"You are working for a bank. You are an AI processing reviews about financial products. You need to triage the reviews into positive and negative ones.\nYou will always answer with a JSON document, and only this JSON document.\n\nYour task is to process the review delimited by ---.\nApply a sentiment analysis to the passed review to determine if it is positive or negative.\nThe review can be in any language. So, you will need to identify the language.\n\nFor example:\n- \"I love your bank, you are the best!\", this is a 'POSITIVE' review\n- \"J'adore votre banque\", this is a 'POSITIVE' review\n- \"I hate your bank, you are the worst!\", this is a 'NEGATIVE' review\n\n Answer with a JSON document containing:\n- the 'evaluation' key set to 'POSITIVE' if the review is positive, 'NEGATIVE' otherwise, depending if the review is positive or negative\n- the 'message' key set to a message thanking the customer in the case of a positive review, or an apology and a note that the bank is going to contact the customer in the case of a negative review. These messages must be polite and use the same language as the passed review.\n\n---\nIm good\n---\n\nYou must answer strictly in the following JSON format: {\n\"evaluation\": (type: enum, must be one of [POSITIVE, NEGATIVE]),\n\"message\": (type: string)\n}","options":{"temperature":0.8,"num_predict":128,"top_p":0.9,"top_k":40},"stream":false}

2024-04-30 12:00:25,012 INFO  [io.qua.lan.oll.OllamaRestApi$OllamaLogger] (vert.x-eventloop-thread-1) Response:
- status code: 200
- headers: [Content-Type: application/json; charset=utf-8], [Date: Tue, 30 Apr 2024 15:00:25 GMT], [Transfer-Encoding: chunked]
- body: {"model":"mistral:7b-instruct-q4_K_M","created_at":"2024-04-30T15:00:25.008819Z","response":"{\n\"evaluation\": \"POSITIVE\",\n\"message\": \"Thank you for your positive feedback! We're glad to hear that you're happy with our bank.\"\n}","done":true,"context":[733,16289,28793,28705,995,460,2739,354,264,5206,28723,995,460,396,16107,9457,10237,684,5593,4076,28723,995,927,298,2629,465,272,10237,778,5278,304,7087,4413,28723,13,1976,622,1743,4372,395,264,9292,3248,28725,304,865,456,9292,3248,28723,13,13,11159,3638,349,298,1759,272,4058,882,321,1345,486,11940,28723,13,21597,264,21790,5643,298,272,4568,4058,298,7655,513,378,349,5278,442,7087,28723,13,1014,4058,541,347,297,707,3842,28723,1537,28725,368,622,927,298,9051,272,3842,28723,13,13,2565,2757,28747,13,28733,345,28737,2016,574,5206,28725,368,460,272,1489,28808,548,456,349,264,464,16953,1153,8138,28742,4058,13,28733,345,28798,28742,316,431,10454,267,8743,1011,548,456,349,264,464,16953,1153,8138,28742,4058,13,28733,345,28737,7665,574,5206,28725,368,460,272,8748,28808,548,456,349,264,464,4390,28777,962,8138,28742,4058,13,13,26307,395,264,9292,3248,8707,28747,13,28733,272,464,20866,352,28742,1945,808,298,464,16953,1153,8138,28742,513,272,4058,349,5278,28725,464,4390,28777,962,8138,28742,5860,28725,10085,513,272,4058,349,5278,442,7087,13,28733,272,464,1324,28742,1945,808,298,264,2928,6979,288,272,6346,297,272,1222,302,264,5278,4058,28725,442,396,979,2161,304,264,5039,369,272,5206,349,1404,298,3754,272,6346,297,272,1222,302,264,7087,4058,28723,2957,8570,1580,347,27057,304,938,272,1348,3842,390,272,4568,4058,28723,13,13,7395,13,2012,1179,13,7395,13,13,1976,1580,4372,19470,297,272,2296,9292,5032,28747,371,13,28739,20866,352,1264,325,1123,28747,10873,28725,1580,347,624,302,733,16953,1153,8138,28725,9991,28777,962,8138,11501,13,28739,1324,1264,325,1123,28747,1423,28731,13,28752,733,28748,16289,28793,13,28751,13,28739,20866,352,1264,345,16953,1153,8138,548,13,28739,1324,1264,345,15896,368,354,574,5278,12139,28808,816,28742,267,8200,298,3934,369,368,28742,267,4610,395,813,5206,611,13,28752],"total_duration":7376358766,"load_duration":3202459,"prompt_eval_duration":202030000,"eval_count":41,"eval_duration":7169513000}
```

If the input it's: `Im good, and you?` the LLM answer sometimes starts with ```json:

```
2024-04-30 12:01:29,128 INFO  [io.qua.lan.oll.OllamaRestApi$OllamaLogger] (vert.x-eventloop-thread-1) Request:
- method: POST
- url: http://localhost:11434/api/generate
- headers: [Accept: application/json], [Content-Type: application/json], [User-Agent: Resteasy Reactive Client], [content-length: 1460]
- body: {"model":"mistral:7b-instruct-q4_K_M","prompt":"You are working for a bank. You are an AI processing reviews about financial products. You need to triage the reviews into positive and negative ones.\nYou will always answer with a JSON document, and only this JSON document.\n\nYour task is to process the review delimited by ---.\nApply a sentiment analysis to the passed review to determine if it is positive or negative.\nThe review can be in any language. So, you will need to identify the language.\n\nFor example:\n- \"I love your bank, you are the best!\", this is a 'POSITIVE' review\n- \"J'adore votre banque\", this is a 'POSITIVE' review\n- \"I hate your bank, you are the worst!\", this is a 'NEGATIVE' review\n\n Answer with a JSON document containing:\n- the 'evaluation' key set to 'POSITIVE' if the review is positive, 'NEGATIVE' otherwise, depending if the review is positive or negative\n- the 'message' key set to a message thanking the customer in the case of a positive review, or an apology and a note that the bank is going to contact the customer in the case of a negative review. These messages must be polite and use the same language as the passed review.\n\n---\nIm good, and you?\n---\n\nYou must answer strictly in the following JSON format: {\n\"evaluation\": (type: enum, must be one of [POSITIVE, NEGATIVE]),\n\"message\": (type: string)\n}","options":{"temperature":0.8,"num_predict":128,"top_p":0.9,"top_k":40},"stream":false}

2024-04-30 12:01:58,294 INFO  [io.qua.lan.oll.OllamaRestApi$OllamaLogger] (vert.x-eventloop-thread-1) Response:
- status code: 200
- headers: [Content-Type: application/json; charset=utf-8], [Date: Tue, 30 Apr 2024 15:01:58 GMT], [Transfer-Encoding: chunked]
- body: {"model":"mistral:7b-instruct-q4_K_M","created_at":"2024-04-30T15:01:58.290265Z","response":"```json\n{\n  \"evaluation\": \"NEGATIVE\",\n  \"message\": \"Dear customer, we are sorry to hear that you had a negative experience with our bank. We understand how important it is for you to have a positive experience with your financial provider and we apologize for any inconvenience this may have caused. Our team will reach out to you to discuss the issue and find a solution that works best for you.\"\n}\n```","done":true,"context":[733,16289,28793,28705,995,460,2739,354,264,5206,28723,995,460,396,16107,9457,10237,684,5593,4076,28723,995,927,298,2629,465,272,10237,778,5278,304,7087,4413,28723,13,1976,622,1743,4372,395,264,9292,3248,28725,304,865,456,9292,3248,28723,13,13,11159,3638,349,298,1759,272,4058,882,321,1345,486,11940,28723,13,21597,264,21790,5643,298,272,4568,4058,298,7655,513,378,349,5278,442,7087,28723,13,1014,4058,541,347,297,707,3842,28723,1537,28725,368,622,927,298,9051,272,3842,28723,13,13,2565,2757,28747,13,28733,345,28737,2016,574,5206,28725,368,460,272,1489,28808,548,456,349,264,464,16953,1153,8138,28742,4058,13,28733,345,28798,28742,316,431,10454,267,8743,1011,548,456,349,264,464,16953,1153,8138,28742,4058,13,28733,345,28737,7665,574,5206,28725,368,460,272,8748,28808,548,456,349,264,464,4390,28777,962,8138,28742,4058,13,13,26307,395,264,9292,3248,8707,28747,13,28733,272,464,20866,352,28742,1945,808,298,464,16953,1153,8138,28742,513,272,4058,349,5278,28725,464,4390,28777,962,8138,28742,5860,28725,10085,513,272,4058,349,5278,442,7087,13,28733,272,464,1324,28742,1945,808,298,264,2928,6979,288,272,6346,297,272,1222,302,264,5278,4058,28725,442,396,979,2161,304,264,5039,369,272,5206,349,1404,298,3754,272,6346,297,272,1222,302,264,7087,4058,28723,2957,8570,1580,347,27057,304,938,272,1348,3842,390,272,4568,4058,28723,13,13,7395,13,2012,1179,28725,304,368,28804,13,7395,13,13,1976,1580,4372,19470,297,272,2296,9292,5032,28747,371,13,28739,20866,352,1264,325,1123,28747,10873,28725,1580,347,624,302,733,16953,1153,8138,28725,9991,28777,962,8138,11501,13,28739,1324,1264,325,1123,28747,1423,28731,13,28752,733,28748,16289,28793,13,13940,28832,3371,13,28751,13,28705,345,20866,352,1264,345,4390,28777,962,8138,548,13,28705,345,1324,1264,345,28757,644,6346,28725,478,460,7371,298,3934,369,368,553,264,7087,2659,395,813,5206,28723,816,2380,910,2278,378,349,354,368,298,506,264,5278,2659,395,574,5593,9782,304,478,15884,653,354,707,17910,1434,1640,456,993,506,7334,28723,3489,1918,622,4563,575,298,368,298,3342,272,4291,304,1300,264,5165,369,3791,1489,354,368,611,13,28752,13,13940,28832],"total_duration":29162285922,"load_duration":1921102,"prompt_eval_count":59,"prompt_eval_duration":7878416000,"eval_count":100,"eval_duration":21278943000}



```


SOmetimes the answer format its ok:

```
2024-04-30 12:05:56,797 INFO  [io.qua.lan.oll.OllamaRestApi$OllamaLogger] (vert.x-eventloop-thread-0) Request:
- method: POST
- url: http://localhost:11434/api/generate
- headers: [Accept: application/json], [Content-Type: application/json], [User-Agent: Resteasy Reactive Client], [content-length: 1460]
- body: {"model":"mistral:7b-instruct-q4_K_M","prompt":"You are working for a bank. You are an AI processing reviews about financial products. You need to triage the reviews into positive and negative ones.\nYou will always answer with a JSON document, and only this JSON document.\n\nYour task is to process the review delimited by ---.\nApply a sentiment analysis to the passed review to determine if it is positive or negative.\nThe review can be in any language. So, you will need to identify the language.\n\nFor example:\n- \"I love your bank, you are the best!\", this is a 'POSITIVE' review\n- \"J'adore votre banque\", this is a 'POSITIVE' review\n- \"I hate your bank, you are the worst!\", this is a 'NEGATIVE' review\n\n Answer with a JSON document containing:\n- the 'evaluation' key set to 'POSITIVE' if the review is positive, 'NEGATIVE' otherwise, depending if the review is positive or negative\n- the 'message' key set to a message thanking the customer in the case of a positive review, or an apology and a note that the bank is going to contact the customer in the case of a negative review. These messages must be polite and use the same language as the passed review.\n\n---\nIm good, and you?\n---\n\nYou must answer strictly in the following JSON format: {\n\"evaluation\": (type: enum, must be one of [POSITIVE, NEGATIVE]),\n\"message\": (type: string)\n}","options":{"temperature":0.8,"num_predict":128,"top_p":0.9,"top_k":40},"stream":false}

2024-04-30 12:06:14,016 INFO  [io.qua.lan.oll.OllamaRestApi$OllamaLogger] (vert.x-eventloop-thread-0) Response:
- status code: 200
- headers: [Content-Type: application/json; charset=utf-8], [Date: Tue, 30 Apr 2024 15:06:14 GMT], [Transfer-Encoding: chunked]
- body: {"model":"mistral:7b-instruct-q4_K_M","created_at":"2024-04-30T15:06:14.009937Z","response":"{\n\"evaluation\": \"NEGATIVE\",\n\"message\": \"Dear customer, we are sorry to hear that you had a negative experience with our bank. We take all feedback seriously and we will be reaching out to you as soon as possible to address your concerns.\"\n}","done":true,"context":[733,16289,28793,28705,995,460,2739,354,264,5206,28723,995,460,396,16107,9457,10237,684,5593,4076,28723,995,927,298,2629,465,272,10237,778,5278,304,7087,4413,28723,13,1976,622,1743,4372,395,264,9292,3248,28725,304,865,456,9292,3248,28723,13,13,11159,3638,349,298,1759,272,4058,882,321,1345,486,11940,28723,13,21597,264,21790,5643,298,272,4568,4058,298,7655,513,378,349,5278,442,7087,28723,13,1014,4058,541,347,297,707,3842,28723,1537,28725,368,622,927,298,9051,272,3842,28723,13,13,2565,2757,28747,13,28733,345,28737,2016,574,5206,28725,368,460,272,1489,28808,548,456,349,264,464,16953,1153,8138,28742,4058,13,28733,345,28798,28742,316,431,10454,267,8743,1011,548,456,349,264,464,16953,1153,8138,28742,4058,13,28733,345,28737,7665,574,5206,28725,368,460,272,8748,28808,548,456,349,264,464,4390,28777,962,8138,28742,4058,13,13,26307,395,264,9292,3248,8707,28747,13,28733,272,464,20866,352,28742,1945,808,298,464,16953,1153,8138,28742,513,272,4058,349,5278,28725,464,4390,28777,962,8138,28742,5860,28725,10085,513,272,4058,349,5278,442,7087,13,28733,272,464,1324,28742,1945,808,298,264,2928,6979,288,272,6346,297,272,1222,302,264,5278,4058,28725,442,396,979,2161,304,264,5039,369,272,5206,349,1404,298,3754,272,6346,297,272,1222,302,264,7087,4058,28723,2957,8570,1580,347,27057,304,938,272,1348,3842,390,272,4568,4058,28723,13,13,7395,13,2012,1179,28725,304,368,28804,13,7395,13,13,1976,1580,4372,19470,297,272,2296,9292,5032,28747,371,13,28739,20866,352,1264,325,1123,28747,10873,28725,1580,347,624,302,733,16953,1153,8138,28725,9991,28777,962,8138,11501,13,28739,1324,1264,325,1123,28747,1423,28731,13,28752,733,28748,16289,28793,13,28751,13,28739,20866,352,1264,345,4390,28777,962,8138,548,13,28739,1324,1264,345,28757,644,6346,28725,478,460,7371,298,3934,369,368,553,264,7087,2659,395,813,5206,28723,816,1388,544,12139,10160,304,478,622,347,13323,575,298,368,390,3403,390,2572,298,2962,574,10864,611,13,28752],"total_duration":17212511804,"load_duration":1352499,"prompt_eval_count":59,"prompt_eval_duration":6936620000,"eval_count":61,"eval_duration":10272778000}

```