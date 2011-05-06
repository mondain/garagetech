@org.hibernate.annotations.TypeDefs({

        @org.hibernate.annotations.TypeDef(
                name = "encryptedString",
                typeClass = org.jasypt.hibernate.type.EncryptedStringType.class,
                parameters = {
                        @org.hibernate.annotations.Parameter(name = "encryptorRegisteredName", value = "strongHibernateEncryptor")
                }
        ),

        @org.hibernate.annotations.TypeDef(
                name = "json",
                typeClass = com.lewdlistings.repository.usertype.JsonUserType.class
        ),

        @org.hibernate.annotations.TypeDef(
                name = "phone",
                typeClass = com.lewdlistings.repository.usertype.PhoneNumberUserType.class
        )
})
package com.lewdlistings.entity;