package com.sonicgdx.sonicswirl;

public class TileMap {
    TileMap(){

    }


    // solid blocks
    private byte[] block = {16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16};
    private byte[] empty = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private byte[] staircase = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

    private byte[][][] steepChunk = {
            {staircase,empty,empty,empty,empty,empty,empty,empty},{block,staircase,empty,empty,empty,empty,empty,empty},
            {block,block,staircase,empty,empty,empty,empty,empty},{block,block,block,staircase,empty,empty,empty,empty},
            {block,block,block,block,staircase,empty,empty,empty},{block,block,block,block,block,staircase,empty,empty},
            {block,block,block,block,block,block,staircase,empty},{block,block,block,block,block,block,block,staircase}};

    private byte[][][] blockChunk = {
            {block,block,block,block,block,block,block,block},{block,block,block,block,block,block,block,block},
            {block,block,block,block,block,block,block,block},{block,block,block,block,block,block,block,block},
            {block,block,block,block,block,block,block,block},{block,block,block,block,block,block,block,block},
            {block,block,block,block,block,block,block,block},{block,block,block,block,block,block,block,block}};

    private byte[][][] emptyChunk = {
            {empty,empty,empty,empty,empty,empty,empty,empty},{empty,empty,empty,empty,empty,empty,empty,empty},
            {empty,empty,empty,empty,empty,empty,empty,empty},{empty,empty,empty,empty,empty,empty,empty,empty},
            {empty,empty,empty,empty,empty,empty,empty,empty},{empty,empty,empty,empty,empty,empty,empty,empty},
            {empty,empty,empty,empty,empty,empty,empty,empty},{empty,empty,empty,empty,empty,empty,empty,empty}};


    // Java classes aren't private by default, are they public?

    // Is it good practice to have multiple batches or only one?

    byte[][][][][] steepMap =
            {
                    {steepChunk,emptyChunk,emptyChunk,emptyChunk},
                    {blockChunk,steepChunk,emptyChunk,emptyChunk},
                    {blockChunk,blockChunk,steepChunk,emptyChunk},
                    {blockChunk,blockChunk,blockChunk,steepChunk}
            };


    // 128x128 chunk - one dimension for x, one dimension for y and the data is a height array
    // one height array makes up a 16x16 block



}
