package com.sonicgdx.sonicswirl;

public class TileMap {

    // solid blocks
    private final byte[] block = {16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16};
    private final byte[] empty = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private final byte[] staircase = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

    private final Tile empt = new Tile();
    private final Tile blockTile = new Tile(block,block,0,true,false);

    private final Tile[][] bChunk = {
            {blockTile,blockTile,blockTile,blockTile,blockTile,blockTile,blockTile,blockTile},
            {blockTile,blockTile,blockTile,blockTile,blockTile,blockTile,blockTile,blockTile},
            {blockTile,blockTile,blockTile,blockTile,blockTile,blockTile,blockTile,blockTile},
            {blockTile,blockTile,blockTile,blockTile,blockTile,blockTile,blockTile,blockTile},
            {blockTile,blockTile,blockTile,blockTile,blockTile,blockTile,blockTile,blockTile},
            {blockTile,blockTile,blockTile,blockTile,blockTile,blockTile,blockTile,blockTile},
            {blockTile,blockTile,blockTile,blockTile,blockTile,blockTile,blockTile,blockTile},
            {blockTile,blockTile,blockTile,blockTile,blockTile,blockTile,blockTile,blockTile}};

    private final Tile[][] eChunk = {
            {empt,empt,empt,empt,empt,empt,empt,empt},
            {empt,empt,empt,empt,empt,empt,empt,empt},
            {empt,empt,empt,empt,empt,empt,empt,empt},
            {empt,empt,empt,empt,empt,empt,empt,empt},
            {empt,empt,empt,empt,empt,empt,empt,empt},
            {empt,empt,empt,empt,empt,empt,empt,empt},
            {empt,empt,empt,empt,empt,empt,empt,empt},
            {empt,empt,empt,empt,empt,empt,empt,empt}

    };

    Tile[][][][] bMap =
            {
                    {bChunk,bChunk,bChunk,bChunk},
                    {bChunk,bChunk,bChunk,bChunk},
                    {bChunk,bChunk,bChunk,bChunk},
                    {bChunk,bChunk,bChunk,bChunk}
            };

    Tile[][][][] eMap =
            {
                    {eChunk,eChunk,eChunk,eChunk},
                    {eChunk,eChunk,eChunk,eChunk},
                    {eChunk,eChunk,eChunk,eChunk},
                    {eChunk,eChunk,eChunk,eChunk}
            };




    // 128x128 chunk - one dimension for x, one dimension for y and the data is a height array
    // one height array makes up a 16x16 block



}
