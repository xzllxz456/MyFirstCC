package org.example;


import java.util.Objects;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import com.owlike.genson.annotation.JsonProperty;

@DataType()
public final class TestKey {
    @Property
    private final String key;

    @Property
    private final TestMe value;

    public TestKey(@JsonProperty("Key") final String key, @JsonProperty("value") final TestMe value) {
        this.key = key;
        this.value = value;
    }

    public String getKey(){
        return key;
    }

    public TestMe getValue(){
        return value;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        TestKey other = (TestKey) obj;

        Boolean recordsAreEquals = this.getValue().equals(other.getValue());
        Boolean keysAreEquals = this.getKey().equals(other.getKey());

        return recordsAreEquals && keysAreEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getKey(), this.getValue());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " [key=" + key + ", value="
                + value + "]";
    }
}
