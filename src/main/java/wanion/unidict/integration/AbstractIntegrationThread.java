package wanion.unidict.integration;

/*
 * Created by WanionCane(https://github.com/WanionCane).
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

import wanion.unidict.UniDict;
import wanion.unidict.module.AbstractModuleThread;
import wanion.unidict.resource.ResourceHandler;

abstract class AbstractIntegrationThread extends AbstractModuleThread
{
    protected final ResourceHandler resourceHandler = UniDict.getResourceHandler();

    public AbstractIntegrationThread(String integrationName)
    {
        super(integrationName, "Integration");
    }
}