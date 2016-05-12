package org.radargun.service;

import org.radargun.Service;
import org.radargun.traits.ContinuousQuery;
import org.radargun.traits.ProvidesTrait;

/**
  * @author vjuranek
  */
@Service(doc = InfinispanEmbeddedService.SERVICE_DESCRIPTION)
public class Infinispan90EmbeddedService extends Infinispan80EmbeddedService {

   @ProvidesTrait
   public ContinuousQuery createContinuousQuery() {
      return new Infinispan90EmbeddedContinuousQuery(this);
   }

}
