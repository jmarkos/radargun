/* 
 * JBoss, Home of Professional Open Source
 * Copyright 2012 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */
package org.radargun.stages.lifecycle;

import java.util.Collection;
import java.util.List;

import org.radargun.DistStageAck;
import org.radargun.config.Property;
import org.radargun.config.Stage;
import org.radargun.stages.AbstractDistStage;

/**
 * Common base for stages that start slaves.
 *
 * @author Radim Vansa &lt;rvansa@redhat.com&gt;
 */
@Stage(doc = "")
public abstract class AbstractServiceStartStage extends AbstractDistStage {

   @Property(doc = "Set of slaves where the start may fail but this will not cause an error. Default is none.")
   protected Collection<Integer> mayFailOn;

   @Override
   public boolean processAckOnMaster(List<DistStageAck> acks) {
      boolean success = true;
      logDurationInfo(acks);
      for (DistStageAck ack : acks) {
         if (ack.isError() && (mayFailOn == null || !mayFailOn.contains(ack.getSlaveIndex()))) {
            log.warn("Received error ack " + ack);
            return false;
         } else if (ack.isError()) {
            log.info("Received allowed error ack " + ack);
         } else {
            log.trace("Received success ack " + ack);
         }
      }
      if (log.isTraceEnabled())
         log.trace("All ack messages were successful");
      return success;
   }
}
