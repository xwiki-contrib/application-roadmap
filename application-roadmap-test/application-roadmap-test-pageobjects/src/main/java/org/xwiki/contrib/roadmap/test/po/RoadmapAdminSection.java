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

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.xwiki.administration.test.po.AdministrationSectionPage;

public class RoadmapAdminSection extends AdministrationSectionPage
{

    public static final String SECTION = "Roadmap";
    @FindBy(className = "roadmap-config")
    private WebElement roadmapItemConfig;

    @FindBy(id = "addStatus")
    private WebElement addStatusButton;

    public RoadmapAdminSection(String section)
    {
        super(section);
    }

    public static RoadmapAdminSection gotoPage() {
        AdministrationSectionPage.gotoPage(SECTION);
        return new RoadmapAdminSection(SECTION);
    }

    public WebElement getRoadmapItemConfig() {
        return roadmapItemConfig;
    }

    public List<WebElement> getRoadmapItemStatuses() {
        return roadmapItemConfig.findElements(By.className("jsonKey"));
    }

    public WebElement getAddStatusButton() {
        return addStatusButton;
    }

    public WebElement addStatus() {
        addStatusButton.click();
        WebElement addedStatus = roadmapItemConfig.findElement(By.xpath("//div[@id='template']/preceding-sibling::div"
            + "[1]"));
        return addedStatus;

    }

    public static void deleteStatusItem(WebElement statusItem) {
        statusItem.findElement(By.xpath("//a[@class='pull-right removeStatus']")).click();
    }

    public void save() {
        this.clickSave(true);
    }

}
