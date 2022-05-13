package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.events.targets.SnsTopic;
import software.amazon.awscdk.services.sns.Topic;
import software.amazon.awscdk.services.sns.subscriptions.EmailSubscription;

//O SNS estará fora da VPC por isso, o serviço que for acessa-la precisará de autorização
public class SnsStack extends Stack {
    private final SnsTopic productEventsTopic;

    public SnsStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public SnsStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        productEventsTopic = SnsTopic.Builder.create(Topic.Builder.create(this, "ProductEventsTopic")
                //Nome do tópico
                .topicName("product-events")
                .build())
                .build();

        productEventsTopic.getTopic().addSubscription(EmailSubscription.Builder.create("montival_junior@yahoo.com.br")
                .json(true)
                .build());

    }

    public SnsTopic getProductEventsTopic() {
        return productEventsTopic;
    }
}
