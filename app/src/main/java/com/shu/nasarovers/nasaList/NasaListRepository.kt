package com.shu.nasarovers.nasaList

import com.shu.nasarovers.api.retrofit
import com.shu.nasarovers.models.Photos
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class NasaListRepository {
    suspend fun getNasaPhotos(sol: Int, rover: String): List<List<Photos>> {

        return coroutineScope {
            val newList = mutableListOf<List<Photos>>()
            var tempList: List<Photos>
            launch {
                repeat(10) {
                    launch {
                        tempList = retrofit.loadPhotos(rover, sol + it).photos
                        if (tempList.isNotEmpty()) {
                            newList.add(tempList)
                        }
                    }
                }
            }.join()
            return@coroutineScope newList.toList()
        }


        /*   var newList = mutableListOf<List<Photos>>()
           var tempList: List<Photos>
           for (i in sol..sol+50) {
               tempList=retrofit.loadPhotos(rover,i).photos
               if (tempList.isNotEmpty()) {
                   newList.add(tempList)
               }
           }
           return newList.toList()*/

        /* return listOf(
             retrofit.loadPhotos(rover,3).photos,
             retrofit.loadPhotosTwo(rover,9).photos,
             retrofit.loadPhotosTwo(rover,3).photos,
             retrofit.loadPhotosTwo(rover,11).photos,
             retrofit.loadPhotosTwo(rover,12).photos,
             retrofit.loadPhotosTwo(rover,14).photos,
             retrofit.loadPhotosTwo(rover,15).photos,
             retrofit.loadPhotosTwo(rover,16).photos,
             retrofit.loadPhotosTwo(rover,17).photos,
             retrofit.loadPhotosTwo(rover,18).photos,
             retrofit.loadPhotosTwo(rover,19).photos,
             retrofit.loadPhotosTwo(rover,20).photos,
             retrofit.loadPhotosTwo(rover,21).photos,
             retrofit.loadPhotosTwo(rover,22).photos,
             retrofit.loadPhotosTwo(rover,23).photos,
             retrofit.loadPhotosTwo(rover,24).photos,
         )*/
    }

    suspend fun getNasaPhotosPages(sol: Int, page: Int, rover: String): List<Photos> {
        return retrofit.loadPhotosPages(rover, sol, page).photos
    }
}

/*
//Rover Opportunity, Spirit, curiosity
//Посадка марсохода PERSEVERANCE п
 */