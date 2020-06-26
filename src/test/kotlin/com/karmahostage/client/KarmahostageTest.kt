package com.karmahostage.client

import org.junit.jupiter.api.Test

internal class KarmahostageTest {

    @Test
    fun test() {
        Karmahostage.builder()
                .apikey("sk_\$2a\$10\$xkcV0UfiXrPZWKv9jck2muGwWCEEcgQLPKZ.BCycSGG7QggFD2F5a")
                .build().secrets()
                .retrieveByKey("application")
    }
}