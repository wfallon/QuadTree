// CIS 121, HW4 QuadTree

public class QuadTreeNodeImpl implements QuadTreeNode {
    
    //pointers to sub trees
    QuadTreeNode upperLeft;
    QuadTreeNode upperRight;
    QuadTreeNode bottomLeft;
    QuadTreeNode bottomRight;
    
    //color value
    int color;
    
    //dimension
    int dimension;
    
    public QuadTreeNodeImpl(int c, int d, QuadTreeNode uL, QuadTreeNode uR, 
            QuadTreeNode bL, QuadTreeNode bR) {
        color = c;
        dimension = d;
        upperLeft = uL;
        upperRight = uR;
        bottomLeft = bL;
        bottomRight = bR;
    }
    
    /**
     * ! Do not delete this method !
     * Please implement your logic inside this method without modifying the signature
     * of this method, or else your code won't compile.
     * <p/>
     * Be careful that if you were to create another method, make sure it is not public.
     *
     * @param image image to put into the tree
     * @return the newly build QuadTreeNode instance which stores the compressed image
     * @throws IllegalArgumentException if image is null
     * @throws IllegalArgumentException if image is empty
     * @throws IllegalArgumentException if image.length is not a power of 2
     * @throws IllegalArgumentException if image, the 2d-array, is not a perfect square
     */
    public static QuadTreeNode buildFromIntArray(int[][] image) {
        if (image == null) {
            throw new IllegalArgumentException();
        } else if (image.length == 0 || image[0].length == 0) {
            throw new IllegalArgumentException();
        } else if ((image.length & (image.length - 1)) != 0) {
            throw new IllegalArgumentException();
        } else if ((image[0].length & (image[0].length - 1)) != 0) {
            throw new IllegalArgumentException();
        } else if ((image.length != image[0].length)) {
            throw new IllegalArgumentException();
        }
        return buildTree(image, 0, image[0].length - 1, 0, image.length - 1);
    }
    
    /**s
     * Recursively builds the QuadTreeNode by calling buildTree on the four quadrants of Image.
     * Stops recursion when we have reached a single node. 
     * 
     * @param image array of pixels to be stored in QuadTree
     * @param lowerX lower x bound of image we are looking at 
     * @param lowerY lower y bound of image we are looking at 
     * @param upperX upper x bound of image we are looking at 
     * @param upperY upper y bound of image we are looking at 
     * @return QuadTreeNode
     */
    private static QuadTreeNode buildTree(int[][] image, int lowerX, int upperX, 
            int lowerY, int upperY) {
        if (lowerX == upperX && lowerY == upperY) {
            return new QuadTreeNodeImpl(image[lowerY][lowerX], 1, null, null, null, null);
        }
        
        QuadTreeNode uL = buildTree(image, lowerX, (upperX + lowerX) / 2, lowerY, 
                (upperY + lowerY) / 2);
        QuadTreeNode uR = buildTree(image, (upperX + lowerX) / 2 + 1, upperX, lowerY, 
                (upperY + lowerY) / 2);
        QuadTreeNode bL = buildTree(image, lowerX, (upperX + lowerX) / 2, 
                (upperY + lowerY) / 2 + 1, upperY);
        QuadTreeNode bR = buildTree(image, (upperX + lowerX) / 2 + 1, upperX, 
                (upperY + lowerY) / 2 + 1, upperY);
        
        //if they are all leaves and all represent the same color, then we can absorb them all 
        //into the parent node
        if (uL.isLeaf() && uR.isLeaf() && bL.isLeaf() && bR.isLeaf() && uL.getColor(0,0) 
                == uR.getColor(0, 0) && uR.getColor(0, 0) == bL.getColor(0, 0) 
                && bL.getColor(0,0) == bR.getColor(0, 0)) {
            return new QuadTreeNodeImpl(uL.getColor(0,0), uL.getDimension() * 2, 
                    null, null, null, null);
        } else {
            return new QuadTreeNodeImpl(0, uL.getDimension() * 2, uL, uR, bL, bR);
        }
    }

    /**
     * Gets the color at coordinate {@code (x, y)}. Find the leaf node that
     * corresponds to this coordinate and get the color of that node.
     * <p/>
     * The coordinate {@code (x, y)} is relative to the node this method is called on.
     * For example, {@code getColor(0, 0)} should give the color located at upper-left most
     * pixel of whatever node it is called on.
     *
     * @param x the {@code x}-coordinate
     * @param y the {@code y}-coordinate
     * @return the color that is on (x, y)
     * @throws IllegalArgumentException if {@code x} or {@code y} is out of bounds
     */
    @Override
    public int getColor(int x, int y) {
        if (x < 0 || x > this.dimension - 1 || y < 0 || y > this.dimension - 1) {
            
            throw new IllegalArgumentException();
        }
        
        if (this.isLeaf()) {
            return this.color;
        } 
        
        if (x < this.dimension / 2 && y < this.dimension / 2) {
            return this.upperLeft.getColor(x, y);
        } else if (x < this.dimension / 2 && y >= this.dimension / 2) {
            return this.bottomLeft.getColor(x, y - this.dimension / 2);
        } else if (x >= this.dimension / 2 && y < this.dimension / 2) {
            return this.upperRight.getColor(x - this.dimension / 2, y);
        } else {
            return this.bottomRight.getColor(x - this.dimension / 2, y - this.dimension / 2);
        }
    }

    /**
     * Sets the color at coordinates {@code (x, y)}. That is, find the leaf node that
     * corresponds to this coordinate and set the color of that node to the specific color.
     * <p/>
     * The coordinate {@code (x, y)} is relative to the node this method is called on.
     * For example, {@code setColor(0, 0)} should set the color located at upper-left most
     * pixel of whatever node it is called on.
     * <p/>
     * Colors of all integers are allowed.
     *
     * @param x     the {@code x}-coordinate
     * @param y     the {@code y}-coordinate
     * @param color the color (x, y) should be set to
     * @throws IllegalArgumentException if {@code x} or {@code y} is out of bounds
     */
    @Override
    public void setColor(int x, int y, int c) {
        if (x < 0 || x > this.dimension - 1 || y < 0 || y > this.dimension - 1) {
            throw new IllegalArgumentException();
        }
        
        if (this.isLeaf() && this.dimension == 1) {
            this.color = c;
        } else {
            //if we are at a leaf need to instantiate child nodes
            if (this.isLeaf()) {
                this.upperLeft = new QuadTreeNodeImpl(this.color, this.dimension / 2,
                        null, null, null, null);
                this.upperRight = new QuadTreeNodeImpl(this.color, this.dimension / 2,
                        null, null, null, null);
                this.bottomLeft = new QuadTreeNodeImpl(this.color, this.dimension / 2,
                        null, null, null, null);
                this.bottomRight = new QuadTreeNodeImpl(this.color, this.dimension / 2,
                        null, null, null, null); 
            }
            
            //continue iterating through until we reach single node with our value
            if (x < this.dimension / 2 && y < this.dimension / 2) {
                this.upperLeft.setColor(x, y, c);
            } else if (x < this.dimension / 2 && y >= this.dimension / 2) {
                this.bottomLeft.setColor(x, y - this.dimension / 2, c);
            } else if (x >= this.dimension / 2 && y < this.dimension / 2) {
                this.upperRight.setColor(x - this.dimension / 2, y, c);
            } else {
                this.bottomRight.setColor(x - this.dimension / 2, y - this.dimension / 2, c);
            }
        }
        
      //if they are all leaves and all represent the same color, then we can absorb them all 
        //into the parent node
        if (!this.isLeaf() && upperLeft.isLeaf() && upperRight.isLeaf() && bottomLeft.isLeaf() 
                && bottomRight.isLeaf() && upperLeft.getColor(0,0) == upperRight.getColor(0, 0) 
                && upperRight.getColor(0, 0) == bottomLeft.getColor(0, 0) 
                && bottomLeft.getColor(0,0) == bottomRight.getColor(0, 0)) {
            this.color = upperLeft.getColor(0, 0);
            this.upperLeft = null;
            this.upperRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        } 
    }

    /**
     * Returns the {@link QuadTreeNode} in the specified quadrant.
     * If this QuadTreeNode is a leaf, then this method returns {@code null}
     * for all quadrants. Otherwise this method returns a non-{@code null} value.
     *
     * @param quadrant the quadrant to retrieve
     * @return quadrant instance or {@code null}
     */
    @Override
    public QuadTreeNode getQuadrant(QuadName quadrant) {
        if (this.isLeaf()) {
            return null;
        } 
        switch (quadrant) {
            case TOP_LEFT:
                return upperLeft;
            
            case TOP_RIGHT:
                return upperRight;
            
            case BOTTOM_LEFT:
                return bottomLeft;
            
            case BOTTOM_RIGHT:
                return bottomRight;
            
            default:
                return null;
        }
    }

    /**
     * Returns the dimensions that this QuadTreeNode represents.
     * <p/>
     * For example, a QuadTreeNode representing a 4x4 area will return {@code 4},
     * and subsequently its direct children (if exists) will return {@code 2}.
     * This value will always be a non-negative integer power of 2.
     *
     * @return the size of the square's side length represented by this QuadTreeNode.
     */
    @Override
    public int getDimension() {
        return this.dimension;
    }

    /**
     * Returns the number of descendants this current node contains (including itself).
     * <p/>
     * For example, a leaf has size {@code 1}, and a QuadTreeNode with 4 children
     * and no grandchildren has size {@code 5}.
     *
     * @return the number of descendants contained by this QuadTreeNode
     */
    @Override
    public int getSize() {
        if (this.isLeaf()) {
            return 1;
        }
        return 1 + upperLeft.getSize() + upperRight.getSize() + bottomLeft.getSize() +
                bottomRight.getSize();
    }

    /**
     * Returns {@code true} if this {@link QuadTreeNode} is a leaf.
     *
     * @return {@code true} if the QuadTreeNode is a leaf
     */
    @Override
    public boolean isLeaf() {
        return upperLeft == null && upperRight == null && bottomLeft == null 
                && bottomRight == null;
    }

    /**
     * Decompresses the QuadTree into a 2d-array. The returned array contains integers that
     * represent the color at each coordinate. The returned 2D array satisfies {@code
     * result[y][x] == getColor(x, y)} for each coordinate {@code (x, y)}.
     *
     * @return a newly initialized array storing the decompressed image data
     */
    @Override
    public int[][] decompress() {
        int[][] decompression = new int[dimension][dimension];
        decompressHelp(decompression, 0, decompression[0].length - 1 , 0, 
                decompression.length - 1, this);
        return decompression;
    }
    
    /**
     * Decompresses the tree back into a 2d array
     * 
     * @param decompression array to be filled
     * @param lowerX lower x index of array we are looking at
     * @param upperX upper x index of array we are looking at
     * @param lowerY lower y index of array we are looking at
     * @param upperY upper y index of array we are looking at
     * @param currTree current Tree we are iterating on
     */
    private void decompressHelp(int[][] decompression, int lowerX, int upperX, 
            int lowerY, int upperY, QuadTreeNode currTree) {
        //once we are at a leaf, we know all the colors are the same so we can fill the
        //respective quadrant up
        if (currTree.isLeaf()) {
            for (int i = lowerX; i <= upperX; i++) {
                for (int j = lowerY; j <= upperY; j++) {
                    decompression[j][i] = currTree.getColor(0,0);
                }
            }
        } else {
            //upper left
            decompressHelp(decompression, lowerX, (upperX + lowerX) / 2, lowerY, 
                    (upperY + lowerY) / 2, currTree.getQuadrant(QuadName.TOP_LEFT));
            
            //upper right
            decompressHelp(decompression, (upperX + lowerX) / 2 + 1, upperX, lowerY, 
                    (upperY + lowerY) / 2, currTree.getQuadrant(QuadName.TOP_RIGHT));
            //bottom left
            decompressHelp(decompression, lowerX, (upperX + lowerX) / 2, 
                    (upperY + lowerY) / 2 + 1, upperY, currTree.getQuadrant(QuadName.BOTTOM_LEFT)); 
            //bottom right
            decompressHelp(decompression,  (upperX + lowerX) / 2 + 1, upperX, 
                    (upperY + lowerY) / 2 + 1, upperY, currTree.getQuadrant(QuadName.BOTTOM_RIGHT));
        }
    }

    /**
     * Gets the compression ratio of the current QuadTree.
     * The compression ratio is defined as the number of {@link QuadTreeNode}s
     * contained in the tree, divided by the number of pixels in the image.
     *
     * @return the compression ratio
     */
    @Override
    public double getCompressionRatio() {
        return (double) this.getSize() / (double) (this.dimension * this.dimension);
    }
}
