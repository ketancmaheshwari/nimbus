/*
 * Copyright 1999-2010 University of Chicago
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.globus.workspace.remoting.admin.defaults;

import org.globus.workspace.remoting.admin.RemoteNodePool;
import org.globus.workspace.scheduler.VmmNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FakeRemoteNodePool implements RemoteNodePool {

    private List<VmmNode> nodeList = new ArrayList<VmmNode>();

    public void addNode(VmmNode node) {
        nodeList.add(node);
    }

    public void addNodes(Collection<VmmNode> nodes) {
        nodeList.addAll(nodes);
    }

    public List<VmmNode> listNodes() {
        return new ArrayList<VmmNode>(nodeList);
    }

    public VmmNode getNode(String hostname) {
        for (VmmNode node : nodeList) {
            if (node.getHostname().equals(hostname)) {
                return node;
            }
        }
        return null;
    }

    public void updateNode(VmmNode node) {
        final String hostname = node.getHostname();
        for (int i = 0; i < this.nodeList.size(); i++) {
            if (nodeList.get(i).getHostname().equals(hostname)) {
                nodeList.set(i, node);
            }
        }
    }

    public void removeNode(String hostname) {
        for (int i = 0; i < this.nodeList.size(); i++) {
            if (nodeList.get(i).getHostname().equals(hostname)) {
                nodeList.remove(i);
            }
        }
    }

    public void removeNodes(Collection<String> hostnames) {
        for (String hostname : hostnames) {
            this.removeNode(hostname);
        }
    }
}
