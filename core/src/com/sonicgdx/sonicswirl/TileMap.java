package com.sonicgdx.sonicswirl;

public class TileMap {

    // solid blocks
    private final byte[] empty = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private final byte[] slope = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
    private final byte[] full = {16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16};
    private final byte[] half = {8,8,8,8,8,8,8,8};
    private final byte[] rvslope = {16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
    private final byte[] tall1 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};

    private final Tile empt = new Tile();
    private final Tile ftile = new Tile(full,full,0,true,false);
    private final Tile rvtile = new Tile(rvslope,rvslope,0,true,false);
    private final Tile sTile = new Tile(slope,slope,0,true,false);

    private final Tile[][] fChunk = {
            {ftile,ftile,ftile,ftile,ftile,ftile,ftile,ftile},
            {ftile,ftile,ftile,ftile,ftile,ftile,ftile,ftile},
            {ftile,ftile,ftile,ftile,ftile,ftile,ftile,ftile},
            {ftile,ftile,ftile,ftile,ftile,ftile,ftile,ftile},
            {ftile,ftile,ftile,ftile,ftile,ftile,ftile,ftile},
            {ftile,ftile,ftile,ftile,ftile,ftile,ftile,ftile},
            {ftile,ftile,ftile,ftile,ftile,ftile,ftile,ftile},
            {ftile,ftile,ftile,ftile,ftile,ftile,ftile,ftile}};

    private final Tile[][] rvChunk = {
            {rvtile,rvtile,rvtile,rvtile,rvtile,rvtile,rvtile,rvtile},
            {rvtile,rvtile,rvtile,rvtile,rvtile,rvtile,rvtile,rvtile},
            {rvtile,rvtile,rvtile,rvtile,rvtile,rvtile,rvtile,rvtile},
            {rvtile,rvtile,rvtile,rvtile,rvtile,rvtile,rvtile,rvtile},
            {rvtile,rvtile,rvtile,rvtile,rvtile,rvtile,rvtile,rvtile},
            {rvtile,rvtile,rvtile,rvtile,rvtile,rvtile,rvtile,rvtile},
            {rvtile,rvtile,rvtile,rvtile,rvtile,rvtile,rvtile,rvtile},
            {rvtile,rvtile,rvtile,rvtile,rvtile,rvtile,rvtile,rvtile}};

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
                    {fChunk,fChunk,fChunk,fChunk},
                    {fChunk,fChunk,fChunk,fChunk},
                    {fChunk,fChunk,fChunk,fChunk},
                    {fChunk,fChunk,fChunk,fChunk}
            };

    Tile[][][][] eMap =
            {
                    {eChunk,eChunk,eChunk,eChunk},
                    {eChunk,eChunk,eChunk,eChunk},
                    {eChunk,eChunk,eChunk,eChunk},
                    {eChunk,eChunk,eChunk,eChunk}
            };

    Tile[][][][] rvMap =
            {
                    {rvChunk,rvChunk,rvChunk,rvChunk},
                    {rvChunk,rvChunk,rvChunk,rvChunk},
                    {rvChunk,rvChunk,rvChunk,rvChunk},
                    {rvChunk,rvChunk,rvChunk,rvChunk},
                    {rvChunk,rvChunk,rvChunk,rvChunk},
                    {rvChunk,rvChunk,rvChunk,rvChunk},
                    {rvChunk,rvChunk,rvChunk,rvChunk},
                    {rvChunk,rvChunk,rvChunk,rvChunk},
                    {rvChunk,rvChunk,rvChunk,rvChunk},
                    {rvChunk,rvChunk,rvChunk,rvChunk},
                    {rvChunk,rvChunk,rvChunk,rvChunk}

            };



    // 128x128 chunk - one dimension for x, one dimension for y and the data is a height array
    // one height array makes up a 16x16 block



}
