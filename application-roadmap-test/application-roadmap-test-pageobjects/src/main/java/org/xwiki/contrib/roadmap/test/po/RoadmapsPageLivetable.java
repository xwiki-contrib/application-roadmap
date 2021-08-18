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

import org.xwiki.test.ui.po.LiveTableElement;

public class RoadmapsPageLivetable extends LiveTableElement
{
    public RoadmapsPageLivetable()
    {
        super("roadmaps");
    }

    public void filterColumn(int columnNumber, String filterValue)
    {
        String columnId = String.format("xwiki-livetable-roadmaps-filter-%s", columnNumber);
        this.filterColumn(columnId, filterValue);
    }

    public void filterByRoadmapTitle(String pageTitle) {
        String columnId = String.format("xwiki-livetable-roadmaps-filter-%s", 1);
        this.filterColumn(columnId, pageTitle);
    }
}
