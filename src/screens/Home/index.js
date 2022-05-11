import { View, Text, ImageBackground, Pressable } from 'react-native'
import React from 'react'
import styles from './styles'
import Fontisto from "react-native-vector-icons/Fontisto"

const HomeScreen = () => {
    const color = "#f15454"
  return (
    <View >
      <ImageBackground source={require("../../../assets/images/wallpaper.jpg")}
                        style={styles.image}>
        <Pressable style={styles.searchButton}
            onPress={() => console.warn("Search Btn Clicked")}>
            <Fontisto name="search" size={25} color={color} />
            <Text style={styles.searchButtonText}>Where are you going?</Text>
        </Pressable>

        <Text style={styles.title}>Get Close Properties</Text>
        <Pressable style={styles.button}
            onPress={() => console.warn("Explore Btn Clicked")}>
            <Text style={styles.buttonText}>Explore Nearby Properties</Text>
        </Pressable>
      </ImageBackground>  
      
    </View>
  )
}

export default HomeScreen