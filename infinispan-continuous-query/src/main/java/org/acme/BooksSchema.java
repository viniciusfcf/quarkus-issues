package org.acme;

import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;
import org.infinispan.protostream.types.java.math.BigDecimalAdapter;

@AutoProtoSchemaBuilder(includeClasses = { Book.class, Author.class, BigDecimalAdapter.class }, schemaPackageName = "book_sample")
interface BookStoreSchema extends GeneratedSchema {
}
