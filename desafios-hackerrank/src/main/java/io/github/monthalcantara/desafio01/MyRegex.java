package io.github.monthalcantara.desafio01;

import java.util.regex.Pattern;

class MyRegex {

    final String ipRegex = "^((1?\\d{1,2}|2([0-4]\\d|5[0-5]))\\.){3}(1?\\d{1,2}|2([0-4]\\d|5[0-5]))$|^$";

    public Boolean isIpValido(final String ip) {
        return Pattern.compile(this.ipRegex)
                .matcher(ip)
                .find();
        /*
        Padrão a ser seguido
        Pattern pattern = Pattern.compile(this.ipRegex);

        Verificação se o padrão foi cumprido
        Matcher matcher = pattern.matcher(ip);

        return matcher.find();
        */
    }
}
