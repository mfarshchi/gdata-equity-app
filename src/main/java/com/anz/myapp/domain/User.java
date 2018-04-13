/*
 * ANZ Project for an Interview
 * 
 * Equity Data Signal App By Mostafa Farshchi
 * Template pack-angular:src/main/java/domain/Entity.java.e.vm
 */
package com.anz.myapp.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.anz.myapp.audit.AuditContextHolder;
import com.anz.myapp.validation.FixedLength;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
@Table(name = "\"USER\"")
public class User implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(User.class.getName());

    // Raw attributes
    private Integer id;
    private String login;
    private String password;
    private String email;
    private Boolean isEnabled;
    private String civility;
    private String countryCode;
    private String firstName;
    private String lastName;
    private Instant creationDate;
    private String creationAuthor;
    private Instant lastModificationDate;
    private String lastModificationAuthor;
    private Integer version;

    @Override
    public String entityClassName() {
        return User.class.getSimpleName();
    }

    // -------------------------------
    // Role names support
    // -------------------------------

    /**
     * Default implementation returns hard coded granted authorities for this account (i.e. "ROLE_USER" and "ROLE_ADMIN").
     * TODO: You should override this method to provide your own custom authorities using your own logic.
     * Or you can follow Celerio Account Table convention. Please refer to Celerio Documentation.
     */
    @Transient
    public List<String> getRoleNames() {
        List<String> roleNames = new ArrayList<String>();
        if ("user".equalsIgnoreCase(getLogin())) {
            roleNames.add("ROLE_USER");
        } else if ("admin".equalsIgnoreCase(getLogin())) {
            roleNames.add("ROLE_USER");
            roleNames.add("ROLE_ADMIN");
        }

        log.warning("Returning hard coded role names. TODO: get the real role names");
        return roleNames;
    }
    // -- [id] ------------------------

    @Override
    @Column(name = "id", precision = 10)
    @GeneratedValue(strategy = IDENTITY)
    @Id
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public User id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [login] ------------------------

    /**
     * The login used to login
     */
    @NotEmpty
    @Size(max = 100)
    @Column(name = "login", nullable = false, unique = true, length = 100)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public User login(String login) {
        setLogin(login);
        return this;
    }
    // -- [password] ------------------------

    @NotEmpty
    @Size(max = 100)
    @Column(name = "\"password\"", nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }
    // -- [email] ------------------------

    @Email
    @Size(max = 100)
    @Column(name = "email", length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }
    // -- [isEnabled] ------------------------

    @NotNull
    @Column(name = "is_enabled", nullable = false, length = 0)
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public User isEnabled(Boolean isEnabled) {
        setIsEnabled(isEnabled);
        return this;
    }
    // -- [civility] ------------------------

    @FixedLength(length = 2)
    @Column(name = "civility", length = 2)
    public String getCivility() {
        return civility;
    }

    public void setCivility(String civility) {
        this.civility = civility;
    }

    public User civility(String civility) {
        setCivility(civility);
        return this;
    }
    // -- [countryCode] ------------------------

    @Size(max = 6)
    @Column(name = "country_code", length = 6)
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public User countryCode(String countryCode) {
        setCountryCode(countryCode);
        return this;
    }
    // -- [firstName] ------------------------

    @Size(max = 100)
    @Column(name = "first_name", length = 100)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public User firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }
    // -- [lastName] ------------------------

    @Size(max = 100)
    @Column(name = "last_name", length = 100)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User lastName(String lastName) {
        setLastName(lastName);
        return this;
    }
    // -- [creationDate] ------------------------

    @Column(name = "creation_date", nullable = false, length = 19)
    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public User creationDate(Instant creationDate) {
        setCreationDate(creationDate);
        return this;
    }
    // -- [creationAuthor] ------------------------

    @Column(name = "creation_author", length = 200)
    public String getCreationAuthor() {
        return creationAuthor;
    }

    public void setCreationAuthor(String creationAuthor) {
        this.creationAuthor = creationAuthor;
    }

    public User creationAuthor(String creationAuthor) {
        setCreationAuthor(creationAuthor);
        return this;
    }
    // -- [lastModificationDate] ------------------------

    @Column(name = "last_modification_date", nullable = false, length = 19)
    public Instant getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Instant lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public User lastModificationDate(Instant lastModificationDate) {
        setLastModificationDate(lastModificationDate);
        return this;
    }
    // -- [lastModificationAuthor] ------------------------

    @Column(name = "last_modification_author", length = 200)
    public String getLastModificationAuthor() {
        return lastModificationAuthor;
    }

    public void setLastModificationAuthor(String lastModificationAuthor) {
        this.lastModificationAuthor = lastModificationAuthor;
    }

    public User lastModificationAuthor(String lastModificationAuthor) {
        setLastModificationAuthor(lastModificationAuthor);
        return this;
    }
    // -- [version] ------------------------

    @Column(name = "version", precision = 10)
    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public User version(Integer version) {
        setVersion(version);
        return this;
    }

    /**
     * Apply the default values.
     */
    public User withDefaults() {
        setIsEnabled(true);
        setCivility("MR");
        setCountryCode("+33");
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof User && hashCode() == other.hashCode());
    }

    private volatile int previousHashCode = 0;

    @Override
    public int hashCode() {
        int hashCode = Objects.hashCode(getLogin());

        if (previousHashCode != 0 && previousHashCode != hashCode) {
            log.warning("DEVELOPER: hashCode has changed!." //
                    + "If you encounter this message you should take the time to carefuly review equals/hashCode for: " //
                    + getClass().getCanonicalName());
        }

        previousHashCode = hashCode;
        return hashCode;
    }

    /**
     * Construct a readable string representation for this User instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("login", getLogin()) //
                .add("password", "XXXX") //
                .add("email", getEmail()) //
                .add("isEnabled", getIsEnabled()) //
                .add("civility", getCivility()) //
                .add("countryCode", getCountryCode()) //
                .add("firstName", getFirstName()) //
                .add("lastName", getLastName()) //
                .add("creationDate", getCreationDate()) //
                .add("creationAuthor", getCreationAuthor()) //
                .add("lastModificationDate", getLastModificationDate()) //
                .add("lastModificationAuthor", getLastModificationAuthor()) //
                .add("version", getVersion()) //
                .toString();
    }

    @PrePersist
    protected void prePersist() {
        if (AuditContextHolder.audit()) {
            setCreationAuthor(AuditContextHolder.username());
            setCreationDate(Instant.now());

        }
    }

    @PreUpdate
    protected void preUpdate() {
        if (AuditContextHolder.audit()) {
            setLastModificationAuthor(AuditContextHolder.username());
            setLastModificationDate(Instant.now());
        }
    }
}