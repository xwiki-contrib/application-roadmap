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
package org.xwiki.contrib.roadmap.test.ui;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.xwiki.contrib.roadmap.test.po.AdministerPage;
import org.xwiki.test.docker.junit5.UITest;
import org.xwiki.test.ui.TestUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the Administration Page.
 *
 * @version $Id$
 */
@UITest
public class AdministrationPageIT
{
    private static final int ROADMAP_STATUS_COUNT = 3;

    @BeforeAll
    public void setup(TestUtils setup) {
        setup.loginAsSuperAdmin();
    }

    @Test
    public void validateTheNumberOfFieldsInPage(TestUtils testUtils) {
        AdministerPage administerPage = AdministerPage.goToPage();
        WebElement roadmapItemStatusConfig = administerPage.getRoadmapItemConfig();
        List<WebElement> roadmapItemStatuses = roadmapItemStatusConfig.findElements(By.className("jsonKey"));

        assertTrue(roadmapItemStatuses.size() == ROADMAP_STATUS_COUNT);
    }

    @Test
    public void validateAdditionOfStatus() {
        AdministerPage administerPage = AdministerPage.goToPage();
        administerPage.addStatus();

        WebElement roadmapItemStatusConfig = administerPage.getRoadmapItemConfig();
        List<WebElement> roadmapItemStatuses = roadmapItemStatusConfig.findElements(By.className("jsonKey"));
        assertTrue(roadmapItemStatuses.size() == ROADMAP_STATUS_COUNT + 1);

    }

}
