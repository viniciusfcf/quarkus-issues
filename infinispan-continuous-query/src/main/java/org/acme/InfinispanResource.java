package org.acme;

import java.math.BigDecimal;
import java.util.Collections;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.Search;
import org.infinispan.commons.configuration.XMLStringConfiguration;
import org.infinispan.commons.util.CloseableIterator;

import io.quarkus.infinispan.client.Remote;
import io.quarkus.runtime.StartupEvent;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;

@Path("/infinispan")
public class InfinispanResource {

   private static final String CACHE_CONFIG =
         "<infinispan><cache-container>" +
               "<distributed-cache name=\"%s\"></distributed-cache>" +
               "</cache-container></infinispan>";


   //@Inject @Remote("myBookCache")
   //init method
   RemoteCache<String, Book> myBookCache;

   @Inject
   RemoteCacheManager remoteCacheManager;

   void init(@Observes StartupEvent e) {

      myBookCache = remoteCacheManager.administration().getOrCreateCache("myBookCache", new XMLStringConfiguration(String.format(CACHE_CONFIG, "myBookCache")));

      QueryFactory queryFactory = Search.getQueryFactory(myBookCache);
      Query query = queryFactory.create("select title from book_sample.Book");
      Search.getContinuousQuery(myBookCache).addContinuousQueryListener(query, new QueryListener());
      
   }

   @GET
   @Path("put")
   public Book put() {
      Book book = new Book("Titulo", "Descricao Legal", 112, Collections.emptySet(), BigDecimal.TEN);
      myBookCache.put("key", book);


    book = new Book("Titulo 2", "Descricao Legal", 112, Collections.emptySet(), BigDecimal.TEN);
      myBookCache.put("key2", book);

      return myBookCache.get("key");
   }

   @GET
   @Path("search")
   public CloseableIterator<Object> search() {
      return Search.getQueryFactory(myBookCache).create("select title from book_sample.Book ").iterator();
   }
}
