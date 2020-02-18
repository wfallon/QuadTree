import static org.junit.Assert.*;

import org.junit.Test;

public class QuadTreeNodeImplTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void testBuildIntFromArrayNullImage() {
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBuildIntFromArrayEmptyImage() {
        int[][] image = {
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBuildIntFromArrayImageLengthNotPowerOf2() {
        int[][] image = {
                {1, 1, 1, 1},
                {1, 2, 1, 1},
                {1, 1, 1, 1}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBuildIntFromArrayImageLengthAlsoNotPowerOf2() {
        int[][] image = {
                {1, 1, 1},
                {1, 2, 1},
                {1, 1, 1},
                {1, 1, 1} 
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBuildIntFromArrayImageHeightNotEqualToLength() {
        int[][] image = {
                {1, 1, 1, 1},
                {1, 2, 1, 1}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
    }
    
    @Test
    public void testBuildIntFromArray() {
        int[][] image = {
                {1, 1, 1, 1},
                {1, 2, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        assertEquals(9, tree.getSize());
        assertEquals(2, tree.getColor(1, 1));
    }
    
    @Test
    public void testBuildIntFromArray2() {
        int[][] image = {
                {0, 1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 0, 1},
                {2, 3, 4, 5}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        assertEquals(21, tree.getSize());
        assertEquals(5, tree.getColor(1, 1));
    }
    
    @Test
    public void testBuildIntFromArray3() {
        int[][] image = {
                {1}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        assertEquals(1, tree.getSize());
        assertEquals(1, tree.getColor(0, 0));
    }
    
    @Test
    public void testBuildIntFromBiggerArray() {
        int[][] image = {
                {0, 0, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        assertEquals(5, tree.getSize());
        assertEquals(1, tree.getColor(1, 5));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetColorIllegalArgument() {
        int[][] image = {
                {0, 1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 0, 1},
                {2, 3, 4, 5}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        assertEquals(0, tree.getColor(5, 0));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetColorIllegalArgument2() {
        int[][] image = {
                {0, 1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 0, 1},
                {2, 3, 4, 5}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        assertEquals(0, tree.getColor(0, 5));
    }
    
    @Test
    public void testGetColor() {
        int[][] image = {
                {1, 1, 1, 1},
                {1, 2, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        assertEquals(2, tree.getColor(1, 1));
        assertEquals(1, tree.getColor(0, 0));
    }
    
    @Test
    public void testGetColor2() {
        int[][] image = {
                {0, 1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 0, 1},
                {2, 3, 4, 5}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        assertEquals(0, tree.getColor(0, 0));
        assertEquals(6, tree.getColor(2, 1));
        assertEquals(3, tree.getColor(1, 3));
        assertEquals(5, tree.getColor(3, 3));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetColorIllegalArgument() {
        int[][] image = {
                {0, 1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 0, 1},
                {2, 3, 4, 5}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        tree.setColor(5, 0, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetColorIllegalArgument2() {
        int[][] image = {
                {0, 1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 0, 1},
                {2, 3, 4, 5}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        tree.setColor(0, 5, 0);
    }
    
    @Test
    public void testSetColor() {
        int[][] image = {
                {1, 1, 1, 1},
                {1, 2, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        tree.setColor(1, 1, 1);
        assertEquals(1, tree.getSize());
        assertEquals(1, tree.getColor(1, 1));
    }
    
    @Test
    public void testSetColor2() {
        int[][] image = {
                {0, 1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 0, 1},
                {2, 3, 4, 5}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        tree.setColor(3, 3, 0);
        assertEquals(21, tree.getSize());
        assertEquals(0, tree.getColor(3, 3));
    }
    
    @Test
    public void testSetColor3() {
        int[][] image = {
                {0, 0, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        tree.setColor(0, 0, 1);
        assertEquals(13, tree.getSize());
        assertEquals(1, tree.getColor(0, 0));
    }
    
    @Test
    public void testSetColor4() {
        int[][] image = {
                {1}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        tree.setColor(0, 0, 0);
        assertEquals(1, tree.getSize());
        assertEquals(0, tree.getColor(0, 0));
    }
    
    @Test
    public void testGetDimension() {
        int[][] image = {
                {0, 0, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        assertEquals(8, tree.getDimension());
    }
    
    @Test
    public void testGetDimension2() {
        int[][] image = {
                {1, 1, 1, 1},
                {1, 2, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        assertEquals(4, tree.getDimension());
    }
    
    @Test
    public void testGetDimension3() {
        int[][] image = {
                {1}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        assertEquals(1, tree.getDimension());
    }
    
    @Test
    public void testGetSize() {
        int[][] image = {
                {1, 1, 1, 1},
                {1, 2, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        assertEquals(9, tree.getSize());
    }
    
    @Test
    public void testGetSize2() {
        int[][] image = {
                {0, 0, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        assertEquals(5, tree.getSize());
    }
    
    @Test
    public void testGetSize3() {
        int[][] image = {
                {1}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        assertEquals(1, tree.getSize());
    }
    
    @Test
    public void testIsLeaf() {
        int[][] image = {
                {1, 1, 1, 1},
                {1, 2, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        assertFalse(tree.getQuadrant(QuadTreeNode.QuadName.TOP_LEFT).isLeaf());
        assertTrue(tree.getQuadrant(QuadTreeNode.QuadName.TOP_RIGHT).isLeaf());
        assertTrue(tree.getQuadrant(QuadTreeNode.QuadName.BOTTOM_LEFT).isLeaf());
        assertTrue(tree.getQuadrant(QuadTreeNode.QuadName.BOTTOM_RIGHT).isLeaf());
    }
    
    @Test
    public void testGetQuadrantLeaf() {
        int[][] image = {
                {1}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        assertNull(tree.getQuadrant(QuadTreeNode.QuadName.TOP_LEFT));
    }
    
    @Test
    public void testDecompress() {
        int[][] image = {
                {0, 0, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        assertArrayEquals(image, tree.decompress());
    }
    
    @Test
    public void testDecompress2() {
        int[][] image = {
                {1, 1, 1, 1},
                {1, 2, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        assertArrayEquals(image, tree.decompress());
    }
    
    @Test
    public void testDecompress3() {
        int[][] image = {
                {1}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        assertArrayEquals(image, tree.decompress());
    }
    
    @Test
    public void testGetCompressionRatio() {
        int[][] image = {
                {1, 1, 1, 1},
                {1, 2, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
            };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(image);
        assertEquals(9.0 / 16.0, tree.getCompressionRatio(), .01);
    }
}
