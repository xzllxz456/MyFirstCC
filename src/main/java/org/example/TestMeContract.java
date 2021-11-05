/*
 * SPDX-License-Identifier: Apache-2.0
 */
package org.example;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.contract.annotation.Contact;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.License;
import org.hyperledger.fabric.shim.ChaincodeStub;

import com.owlike.genson.Genson;


import static java.nio.charset.StandardCharsets.UTF_8;


@Contract(name = "TestMeContract",
        info = @Info(title = "Test Me contract",
                description = "My Smart Contract",
                version = "0.0.1",
                license =
                @License(name = "Apache-2.0",
                        url = ""),
                contact =  @Contact(email = "aa@example.com",
                        name = "aa",
                        url = "http://aa.me")))
@Default
public class TestMeContract implements ContractInterface {
    private final Genson genson = new Genson();

    public TestMeContract() {

    }

    /**
     * Retrieves a car with the specified key from the ledger.
     *
     * @param ctx the transaction context
     * @param key the key
     * @return the Car found on the ledger if there was one
     */
    @Transaction()
    public boolean myExists(Context ctx, String key) {
        byte[] buffer = ctx.getStub().getState(key);
        return (buffer != null && buffer.length > 0);
    }


    /**
     * Retrieves a car with the specified key from the ledger.
     *
     * @param ctx the transaction context
     * @param key the key
     * @param name the name
     * @param id the id
     * @param token the name
     * @return the Car found on the ledger if there was one
     */
    @Transaction()
    public void createMe(Context ctx, String key,String name,String id, String token) {
        boolean exists = myExists(ctx,key);
        ChaincodeStub stub = ctx.getStub();
        if (exists) {
            throw new RuntimeException("The asset "+key+" already exists");
        }

        String myState = stub.getStringState(key);
        TestMe asset = new TestMe(name, id, token);
        myState = genson.serialize(asset);
//        asset.toJSONString();
        stub.putStringState(key,myState);
//        ctx.getStub().putState(key, asset.toJSONString().getBytes(UTF_8));
    }

    /**
     * Retrieves a car with the specified key from the ledger.
     *
     * @param ctx the transaction context
     * @param key the key
     * @return the Car found on the ledger if there was one
     */
    @Transaction()
    public TestMe myReadTest(Context ctx, String key) {
        ChaincodeStub stub = ctx.getStub();
        boolean exists = myExists(ctx,key);
        if (!exists) {
            throw new RuntimeException("The asset "+key+" does not exist");
        }
//        String myState = stub.getStringState(key);
//        TestMe me = genson.deserialize(myState, TestMe.class);

        TestMe newAsset = TestMe.fromJSONString(new String(ctx.getStub().getState(key),UTF_8));
        return newAsset;
    }
    /**
     *      * Retrieves a car with the specified key from the ledger.
     *
     * @param ctx the transaction context
     * @param key the key
     * @param name the name
     * @param token the name
     * @return the Car found on the ledger if there was one
     */
    @Transaction()
    public void myUpdateJava(Context ctx, String key, String name, String token) {
        ChaincodeStub stub = ctx.getStub();
        boolean exists = myExists(ctx,key);
        if (!exists) {
            throw new RuntimeException("The asset "+key+" does not exist");
        }
        TestMe me = genson.deserialize(stub.getStringState(key), TestMe.class);
        TestMe asset = new TestMe(name, me.getId(), token);
        String newMeStrate = genson.serialize(asset);
        stub.putStringState(key, newMeStrate);
//        asset.setValue(newValue);

//        ctx.getStub().putState(key, asset.toJSONString().getBytes(UTF_8));
    }

    @Transaction()
    public void myDeleteJava(Context ctx, String key) {
        boolean exists = myExists(ctx,key);
        if (!exists) {
            throw new RuntimeException("The asset "+key+" does not exist");
        }
        ctx.getStub().delState(key);
    }

}
