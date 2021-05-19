package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.ec2.Vpc;

public class VpcStack extends Stack {
    public VpcStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public VpcStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        /*
         * Para criar uma vpc eu preciso de um escopo, que seria a classe onde eu estou 'this'
         * e mais um nome para identificar o servico.
         */
       Vpc.Builder.create(this, "Vpc01")
               //Quantidade maximas de zonas de disponibilidades que minha vpc atuará
               .maxAzs(3)
               .build();
    }
}
