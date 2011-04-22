@org.hibernate.annotations.TypeDefs({

        @org.hibernate.annotations.TypeDef(
                name = "encryptedString",
                typeClass = org.jasypt.hibernate.type.EncryptedStringType.class,
                parameters = {
                        @org.hibernate.annotations.Parameter(name = "encryptorRegisteredName", value = "strongHibernateEncryptor")
                }
        )
})
package com.lewdlistings.entity;