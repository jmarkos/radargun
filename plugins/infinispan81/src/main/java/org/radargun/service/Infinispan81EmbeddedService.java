package org.radargun.service;

import org.radargun.Service;
import org.radargun.traits.ProvidesTrait;

/**
 * @author vjuranek
 */
@Service(doc = InfinispanEmbeddedService.SERVICE_DESCRIPTION)
public class Infinispan81EmbeddedService extends Infinispan80EmbeddedService {

   @ProvidesTrait
   public Infinispan81EmbeddedContinuousQuery createContinuousQuery() {
      return new Infinispan81EmbeddedContinuousQuery(this);
   }

}
