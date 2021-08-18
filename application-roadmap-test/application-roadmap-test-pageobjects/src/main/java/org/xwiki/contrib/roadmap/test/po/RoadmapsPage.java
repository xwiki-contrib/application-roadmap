/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.contrib.roadmap.test.po;

import org.xwiki.test.ui.po.CreatePagePage;
import org.xwiki.test.ui.po.ViewPage;

public class RoadmapsPage extends ViewPage
{

    public static RoadmapsPage gotoPage() {
        getUtil().gotoPage("Roadmaps", "WebHome");
        return new RoadmapsPage();
    }
    public RoadmapsCreatePage pressCreateButton() {
        getUtil().gotoPage("Roadmaps", "WebHome", "create");
        return new RoadmapsCreatePage();
    }
    public RoadmapPage createNewRoadmapPage(String title) {
        getUtil().gotoPage("Roadmaps", "WebHome", "create");

        CreatePagePage createPagePage = new CreatePagePage();
        createPagePage.fillForm(title, "Roadmaps", title, false);
        createPagePage.clickCreate();

        return (new RoadmapEditPage()).saveAndView();
    }
    public RoadmapsPageLivetable getLiveTable() {
        RoadmapsPageLivetable liveTableElement = new RoadmapsPageLivetable();
        liveTableElement.waitUntilReady();
        return liveTableElement;
    }

}
