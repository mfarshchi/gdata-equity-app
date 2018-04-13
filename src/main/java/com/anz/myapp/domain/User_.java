/*
 * ANZ Project for an Interview
 * 
 * Equity Data Signal App By Mostafa Farshchi
 * Template pack-angular:src/main/java/domain/EntityMeta_.java.e.vm
 */
package com.anz.myapp.domain;

import java.time.Instant;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public abstract class User_ {

    // Raw attributes
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> login;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, Boolean> isEnabled;
    public static volatile SingularAttribute<User, String> civility;
    public static volatile SingularAttribute<User, String> countryCode;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, Instant> creationDate;
    public static volatile SingularAttribute<User, String> creationAuthor;
    public static volatile SingularAttribute<User, Instant> lastModificationDate;
    public static volatile SingularAttribute<User, String> lastModificationAuthor;
    public static volatile SingularAttribute<User, Integer> version;
}