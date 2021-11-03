/*
 * SPDX-License-Identifier: Apache License 2.0
 */

package org.example;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


public final class TestContractTest {

    @Nested
    class AssetExists {
        @Test
        public void noProperAsset() {

            TestMeContract contract = new  TestMeContract();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);

            when(stub.getState("10001")).thenReturn(new byte[] {});
            boolean result = contract.javaExists(ctx,"10001");

            assertFalse(result);
        }

        @Test
        public void assetExists() {

            TestMeContract contract = new  TestMeContract();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);

            when(stub.getState("10001")).thenReturn(new byte[] {42});
            boolean result = contract.javaExists(ctx,"10001");

            assertTrue(result);

        }

        @Test
        public void noKey() {
            TestMeContract contract = new  TestMeContract();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);

            when(stub.getState("10002")).thenReturn(null);
            boolean result = contract.javaExists(ctx,"10002");

            assertFalse(result);

        }

    }

    // @Nested
    // class AssetCreates {

    //     @Test
    //     public void newAssetCreate() {
    //         TestContract contract = new  TestContract();
    //         Context ctx = mock(Context.class);
    //         ChaincodeStub stub = mock(ChaincodeStub.class);
    //         when(ctx.getStub()).thenReturn(stub);

    //         String json = "{\"value\":\"TheTest\"}";

    //         contract.createTest(ctx, "10001", "TheTest");

    //         verify(stub).putState("10001", json.getBytes(UTF_8));
    //     }

    //     @Test
    //     public void alreadyExists() {
    //         TestContract contract = new  TestContract();
    //         Context ctx = mock(Context.class);
    //         ChaincodeStub stub = mock(ChaincodeStub.class);
    //         when(ctx.getStub()).thenReturn(stub);

    //         when(stub.getState("10002")).thenReturn(new byte[] { 42 });

    //         Exception thrown = assertThrows(RuntimeException.class, () -> {
    //             contract.createTest(ctx, "10002", "TheTest");
    //         });

    //         assertEquals(thrown.getMessage(), "The asset 10002 already exists");

    //     }

    // }

    // @Test
    // public void assetRead() {
    //     TestContract contract = new  TestContract();
    //     Context ctx = mock(Context.class);
    //     ChaincodeStub stub = mock(ChaincodeStub.class);
    //     when(ctx.getStub()).thenReturn(stub);

    //     Test asset = new  Test();
    //     asset.setValue("Valuable");

    //     String json = asset.toJSONString();
    //     when(stub.getState("10001")).thenReturn(json.getBytes(StandardCharsets.UTF_8));

    //     Test returnedAsset = contract.readTest(ctx, "10001");
    //     assertEquals(returnedAsset.getValue(), asset.getValue());
    // }

    // @Nested
    // class AssetUpdates {
    //     @Test
    //     public void updateExisting() {
    //         TestContract contract = new  TestContract();
    //         Context ctx = mock(Context.class);
    //         ChaincodeStub stub = mock(ChaincodeStub.class);
    //         when(ctx.getStub()).thenReturn(stub);
    //         when(stub.getState("10001")).thenReturn(new byte[] { 42 });

    //         contract.updateTest(ctx, "10001", "updates");

    //         String json = "{\"value\":\"updates\"}";
    //         verify(stub).putState("10001", json.getBytes(UTF_8));
    //     }

    //     @Test
    //     public void updateMissing() {
    //         TestContract contract = new  TestContract();
    //         Context ctx = mock(Context.class);
    //         ChaincodeStub stub = mock(ChaincodeStub.class);
    //         when(ctx.getStub()).thenReturn(stub);

    //         when(stub.getState("10001")).thenReturn(null);

    //         Exception thrown = assertThrows(RuntimeException.class, () -> {
    //             contract.updateTest(ctx, "10001", "TheTest");
    //         });

    //         assertEquals(thrown.getMessage(), "The asset 10001 does not exist");
    //     }

    // }

    // @Test
    // public void assetDelete() {
    //     TestContract contract = new  TestContract();
    //     Context ctx = mock(Context.class);
    //     ChaincodeStub stub = mock(ChaincodeStub.class);
    //     when(ctx.getStub()).thenReturn(stub);
    //     when(stub.getState("10001")).thenReturn(null);

    //     Exception thrown = assertThrows(RuntimeException.class, () -> {
    //         contract.deleteTest(ctx, "10001");
    //     });

    //     assertEquals(thrown.getMessage(), "The asset 10001 does not exist");
    // }

}
