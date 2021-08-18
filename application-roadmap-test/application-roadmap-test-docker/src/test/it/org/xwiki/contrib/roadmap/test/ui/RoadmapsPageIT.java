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

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.xwiki.contrib.roadmap.test.po.RoadmapEditPage;
import org.xwiki.contrib.roadmap.test.po.RoadmapPage;
import org.xwiki.contrib.roadmap.test.po.RoadmapsPage;
import org.xwiki.contrib.roadmap.test.po.RoadmapsPageLivetable;
import org.xwiki.model.reference.DocumentReference;
import org.xwiki.test.docker.junit5.UITest;
import org.xwiki.test.ui.TestUtils;
import org.xwiki.test.ui.po.ViewPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the Roadmaps Page.
 *
 * @version $Id$
 */
@UITest
public class RoadmapsPageIT
{
    private static final String SIMPLE_PAGE_TITLE = "simplePage";
    private static final String ROADMAP_PAGE_TITLE = "roadmapPage";

    private static final String DETAILED_SCOPE_TEXT = "This is some text for testing purposes";

    @BeforeAll
    public void setup(TestUtils setup) {
        setup.loginAsSuperAdmin();

        DocumentReference docRef = new DocumentReference("xwiki", "Roadmaps", SIMPLE_PAGE_TITLE);
        setup.deletePage(docRef);
        docRef = new DocumentReference("xwiki", Arrays.asList("Roadmaps", ROADMAP_PAGE_TITLE), "WebHome");
        setup.deletePage(docRef);

        setup.createPage("Roadmaps", SIMPLE_PAGE_TITLE, "", SIMPLE_PAGE_TITLE);
    }

    @Test
    @Order(1)
    public void validatePageCreation()
    {
        RoadmapsPage page = RoadmapsPage.gotoPage();
        ViewPage newRoadmapPage = page.createNewRoadmapPage(ROADMAP_PAGE_TITLE);

        assertEquals(ROADMAP_PAGE_TITLE, newRoadmapPage.getDocumentTitle());
    }
    @Test
    @Order(2)
    public void validateLivetableDoesNotContainPagesWithoutRoadmapObject()
    {
        RoadmapsPage page = RoadmapsPage.gotoPage();
        RoadmapsPageLivetable livetable = page.getLiveTable();
        livetable.filterByRoadmapTitle(SIMPLE_PAGE_TITLE);
        // we should get only pages that have roadmap object inside
        assertEquals(0, livetable.getRowCount());
    }
    @Test
    @Order(3)
    public void validateLivetableContainsWithRoadmapObject()
    {
        RoadmapsPage page = RoadmapsPage.gotoPage();
        RoadmapsPageLivetable livetable = page.getLiveTable();
        livetable.filterByRoadmapTitle(ROADMAP_PAGE_TITLE);
        // we should get only pages that have roadmap object inside
        assertEquals(1, livetable.getRowCount());
    }

    @Test
    @Order(4)
    public void validateExistingFieldsOfRoadmapPage(TestUtils testUtils) {
        RoadmapPage roadmapPage = RoadmapPage.gotoPage(ROADMAP_PAGE_TITLE);
        List<WebElement> editableProperties = roadmapPage.getEditableProperties();
        assertEquals(6, editableProperties.size());
    }

    @Test
    @Order(5)
    public void validateTheDetailedScopeEditing() {
        RoadmapEditPage roadmapEditPage = RoadmapEditPage.gotoPage(ROADMAP_PAGE_TITLE);
        roadmapEditPage.setDetailedScope(DETAILED_SCOPE_TEXT);
        RoadmapPage roadmapViewPage = roadmapEditPage.saveAndView();
        String detailedScopeContent = roadmapViewPage.getDetailedScope();
        assertTrue(detailedScopeContent.contains(DETAILED_SCOPE_TEXT));
    }

    @Test
    @Order(6)
    public void validateInsertion() {
        RoadmapEditPage roadmapEditPage = RoadmapEditPage.gotoPage(ROADMAP_PAGE_TITLE);
        roadmapEditPage.setDetailedScope(DETAILED_SCOPE_TEXT);
        RoadmapPage roadmapViewPage = roadmapEditPage.saveAndView();
        String detailedScopeContent = roadmapViewPage.getDetailedScope();
        assertTrue(detailedScopeContent.contains(DETAILED_SCOPE_TEXT));
    }

}
