/*
 * SPDX-License-Identifier: Apache-2.0
 */

package org.example;

import java.util.Objects;
import com.owlike.genson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import com.owlike.genson.Genson;


@DataType()
public final class TestMe {

    private final static Genson genson = new Genson();

    @Property()
    private final String name;

    @Property()
    private final String id;

    @Property()
    private final String token;


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public TestMe(@JsonProperty("name") final String name, @JsonProperty("id") final String id, @JsonProperty("token") final String token){
        this.name = name;
        this.id = id;
        this.token = token;
    }
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        TestMe other = (TestMe) obj;

        return Objects.deepEquals(new String[] {getName(), getId(), getToken()},
                new String[] {other.getName(), other.getId(), other.getToken()});
    }

    @Override
    public int hashCode(){
        return Objects.hash(getName(), getId(), getToken());
    }

    public String toString(){
        return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + "[name=" + name + ", id="
                + id + ", token=" + token + "]";

    }

    public String toJSONString() {
        return genson.serialize(this).toString();
    }

    public static TestMe fromJSONString(String json) {
        TestMe asset = genson.deserialize(json, TestMe.class);
        return asset;
    }
}
