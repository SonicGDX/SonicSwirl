package com.sonicgdx.sonicswirl;

import java.util.Arrays;
import java.util.Collections;

public class TileMap {

    // solid blocks
    private final byte[] empty = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private final byte[] slope = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
    private final byte[] full = {16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16};
    private final byte[] half = {8,8,8,8,8,8,8,8};
    private final byte[] rvslope = {16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
    private final byte[] tall1 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};

    private final Tile empt = new Tile();
    private final Tile ftile = new Tile(full,full,0,(byte) 0,false);
    private final Tile stile = new Tile(slope,slope,0,(byte) 0,false);
    private final Tile rvtile = new Tile(rvslope,rvslope,0,(byte) 0,false);


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
            {ftile,ftile,ftile,ftile,ftile,ftile,ftile,rvtile},
            {ftile,ftile,ftile,ftile,ftile,ftile,rvtile,empt},
            {ftile,ftile,ftile,ftile,ftile,rvtile,empt,empt},
            {ftile,ftile,ftile,ftile,rvtile,empt,empt,empt},
            {ftile,ftile,ftile,rvtile,empt,empt,empt,empt},
            {ftile,ftile,rvtile,empt,empt,empt,empt,empt},
            {ftile,rvtile,empt,empt,empt,empt,empt,empt},
            {rvtile,empt,empt,empt,empt,empt,empt,empt}};

    private final Tile[][] sChunk =
            {
                    {stile,empt,empt,empt,empt,empt,empt,empt},
                    {ftile,stile,empt,empt,empt,empt,empt,empt},
                    {ftile,ftile,stile,empt,empt,empt,empt,empt},
                    {ftile,ftile,ftile,stile,empt,empt,empt,empt},
                    {ftile,ftile,ftile,ftile,stile,empt,empt,empt},
                    {ftile,ftile,ftile,ftile,ftile,stile,empt,empt},
                    {ftile,ftile,ftile,ftile,ftile,ftile,stile,empt},
                    {ftile,ftile,ftile,ftile,ftile,ftile,ftile,stile},
            };




    private final Tile[][] eChunk = {
            Collections.nCopies(8,empt).toArray(new Tile[0]),
            {empt,empt,empt,empt,empt,empt,empt,empt},
            {empt,empt,empt,empt,empt,empt,empt,empt},
            {empt,empt,empt,empt,empt,empt,empt,empt},
            {empt,empt,empt,empt,empt,empt,empt,empt},
            {empt,empt,empt,empt,empt,empt,empt,empt},
            {empt,empt,empt,empt,empt,empt,empt,empt},
            {empt,empt,empt,empt,empt,empt,empt,empt}

    };

    private final Tile[][] borderedChunk = {
            {ftile,ftile,ftile,ftile,ftile,ftile,ftile,ftile},
            {ftile,empt,empt,empt,empt,empt,empt,ftile},
            {ftile,empt,empt,empt,empt,empt,empt,ftile},
            {ftile,empt,empt,empt,empt,empt,empt,ftile},
            {ftile,empt,empt,empt,empt,empt,empt,ftile},
            {ftile,empt,empt,empt,empt,empt,empt,ftile},
            {ftile,empt,empt,empt,empt,empt,empt,ftile},
            {ftile,ftile,ftile,ftile,ftile,ftile,ftile,ftile}

    };

    Tile[][][][] fMap =
            Collections.nCopies(8,Collections.nCopies(8,fChunk).toArray(new Tile[0][0][0])).toArray(new Tile[0][0][0][0]);

    Tile[][][][] eMap =

            Collections.nCopies(8,Collections.nCopies(8,eChunk).toArray(new Tile[0][0][0])).toArray(new Tile[0][0][0][0]);
            /*{
                    Collections.nCopies(8,eChunk).toArray(new Tile[0][0][0]),
                    {eChunk,eChunk,eChunk,eChunk,eChunk,eChunk,eChunk,eChunk},
                    {eChunk,eChunk,eChunk,eChunk,eChunk,eChunk,eChunk,eChunk},
                    {eChunk,eChunk,eChunk,eChunk,eChunk,eChunk,eChunk,eChunk},
                    {eChunk,eChunk,eChunk,eChunk,eChunk,eChunk,eChunk,eChunk},
                    {eChunk,eChunk,eChunk,eChunk,eChunk,eChunk,eChunk,eChunk},
                    {eChunk,eChunk,eChunk,eChunk,eChunk,eChunk,eChunk,eChunk},
                    {eChunk,eChunk,eChunk,eChunk,eChunk,eChunk,eChunk,eChunk}

            };*/
    Tile[][][][] testMap =
            {
                    {sChunk,eChunk,eChunk,eChunk},
                    {fChunk,eChunk,eChunk,eChunk},
                    {fChunk,eChunk,eChunk,eChunk},
                    {fChunk,eChunk,eChunk,eChunk},
                    {fChunk,eChunk,eChunk,eChunk},
                    {fChunk,eChunk,eChunk,eChunk},
                    {fChunk,eChunk,eChunk,eChunk},
                    {rvChunk,eChunk,eChunk,eChunk}

            };



    // 128x128 chunk - one dimension for x, one dimension for y and the data is a height array
    // one height array makes up a 16x16 block



}
