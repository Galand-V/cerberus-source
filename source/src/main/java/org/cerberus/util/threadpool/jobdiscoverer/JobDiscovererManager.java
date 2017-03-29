/**
 * Cerberus Copyright (C) 2013 - 2017 cerberustesting
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This file is part of Cerberus.
 *
 * Cerberus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Cerberus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cerberus.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.cerberus.util.threadpool.jobdiscoverer;

import org.cerberus.util.threadpool.jobdiscoverer.impl.AbstractQueuedSynchronizerFutureTaskImplementationJobDiscoverer;
import org.cerberus.util.threadpool.jobdiscoverer.impl.StateFieldFutureTaskImplementationJobDiscoverer;

import java.util.concurrent.FutureTask;

/**
 * Helper to extract the real task which was submitted to a {@link java.util.concurrent.ThreadPoolExecutor}
 * <p>
 * Initially inspired by the H. M. Kabutz's javaspecialists issue: http://www.javaspecialists.eu/archive/Issue228.html but with the following changes:
 * <p>
 * <ul>
 * <li>be compatible with old {@link FutureTask} implementations before change due to http://bugs.java.com/bugdatabase/view_bug.do?bug_id=7132378</li>
 * <li>change {@link #findRealTask} signature to accept {@link Object} instead of {@link Runnable}</li>
 * <li>reinforce exception catching during class initialization</li>
 * <li>add logger to read exception message easier</li>
 * </ul>
 *
 * @author abourdon
 */
public final class JobDiscovererManager implements JobDiscoverer {

    /**
     * The delegate {@link JobDiscovererManager} according to the current JDK version
     */
    private static JobDiscoverer delegate;

    static {
        // Initialize the JobDiscoverer delegate according to the current JDK version
        for (Class<?> clazz : FutureTask.class.getDeclaredClasses()) {
            if ("java.util.concurrent.FutureTask$Sync".equals(clazz.getName())) {
                delegate = new AbstractQueuedSynchronizerFutureTaskImplementationJobDiscoverer();
                break;
            }
        }
        if (delegate == null) {
            delegate = new StateFieldFutureTaskImplementationJobDiscoverer();
        }
    }

    /**
     * The unique instance of this class
     */
    private static final JobDiscovererManager instance = new JobDiscovererManager();

    /**
     * Get the unique instance of this class
     *
     * @return the unique instance of this class
     */
    public static JobDiscovererManager getInstance() {
        return instance;
    }

    @Override
    public Object findRealTask(final Object task) {
        return delegate.findRealTask(task);
    }

    /**
     * Singleton class
     */
    private JobDiscovererManager() {

    }

}
