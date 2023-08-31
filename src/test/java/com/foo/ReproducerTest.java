package com.foo;

import com.antwerkz.bottlerocket.BottleRocket;
import com.antwerkz.bottlerocket.BottleRocketTest;
import com.github.zafarkhaja.semver.Version;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.filters.Filters;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.testng.annotations.Test;

import java.util.List;

public class ReproducerTest extends BottleRocketTest {
    private Datastore datastore;

    public ReproducerTest() {
        MongoClient mongo = getMongoClient();
        MongoDatabase database = getDatabase();
        database.drop();
        datastore = Morphia.createDatastore(mongo, getDatabase().getName());
        datastore.getMapper().map(MyEmbeddedEntity.class);
        datastore.getMapper().map(MyEntity.class);
    }

    @NotNull
    @Override
    public String databaseName() {
        return "morphia_repro";
    }

    @Nullable
    @Override
    public Version version() {
        return BottleRocket.DEFAULT_VERSION;
    }

    @Test
    public void reproduce() {
        MyEntity entity = new MyEntity();
        String address = "r23c8nwzeqvfnu89hnz";
        entity.setAddress(address);
        MyEmbeddedEntity emb = new MyEmbeddedEntity();
        emb.setName("bar");
        entity.setEmbeddedEntities(List.of(emb));
        datastore.save(entity);
        MyEntity foundEntity = datastore.find(MyEntity.class).filter(Filters.eq("address", address)).first();
        System.out.println(foundEntity.getAddress());
        System.out.println(foundEntity.getEmbeddedEntities().get(0).getName());

    }

}
