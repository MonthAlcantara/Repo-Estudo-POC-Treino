#!/bin/bash
echo "########### Configurando perfil localstack ###########"
aws configure set aws_access_key_id access_key --profile=localstack
aws configure set aws_secret_access_key secret_key --profile=localstack
aws configure set region us-east-1 --profile=localstack

echo "########### Configurando perfil default ###########"
export AWS_DEFAULT_PROFILE=localstack

echo "########### Declarando variaveis de ambiente ###########"
export SNS_TOPIC=purchase-transactions-topic
export SQS_SUBSCRIBER=purchase-transactions-topic-subscriber

echo "########### Creating Subscriber SQS  ###########"
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name "$SQS_SUBSCRIBER"

echo "########### Criando tópico SNS e pegando o arn  ###########"
SNS_TOPIC_ARN=$(aws --endpoint-url=http://localhost:4566 \
  sns create-topic --name=$SNS_TOPIC \
   |  sed 's/"TopicArn"/\n"TopicArn"/g' | grep '"TopicArn"' | awk -F '"TopicArn":' '{print $2}' | tr -d '"' | xargs)

echo "########### Listando topicos SNS ###########"
aws --endpoint-url=http://localhost:4566 sns  list-topics --starting-token=0 --max-items=3

echo "########### Creating subscription for Spring Boot app  ###########"
aws --endpoint-url=http://localhost:4566 \
 sns subscribe \
--topic-arn="$SNS_TOPIC_ARN" \
--protocol=sqs \
--notification-endpoint=http://localhost:4566/000000000000/"$SQS_SUBSCRIBER" \
--return-subscription-arn

echo "########### List subscriptions  ###########"
aws --endpoint-url=http://localhost:4566 sns list-subscriptions

echo "########### Finalizada configuração  ###########"
