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

import java.util.Arrays;

import javax.swing.text.View;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.xwiki.test.ui.po.ViewPage;

public class RoadmapEditPage extends ViewPage
{
    @FindBy(name = "action_save")
    private WebElement saveAndViewButton;

    @FindBy(xpath = "//textarea[@id='content']")
    private WebElement detailedScopeInput;

    public static RoadmapEditPage goToPage(String name) {
        getUtil().gotoPage(Arrays.asList("Roadmaps", name), "WebHome", "edit", "");
        return new RoadmapEditPage();
    }

    public RoadmapPage saveAndView() {
        saveAndViewButton.click();
        return new RoadmapPage();
    }
    public void save() {
        saveAndView();
    }

    public void enterTextToDetailedScopeField(String text) {
        detailedScopeInput.sendKeys(text);
    }
}
