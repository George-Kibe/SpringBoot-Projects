import { View, Text, ImageBackground } from 'react-native'
import React from 'react'
import styles from './styles'


const HomeScreen = () => {
  return (
    <View>
      <ImageBackground source={require("../../../assets/images/wallpaper.jpg")}
                        style={styles.image}>
        <Text style={styles.title}>Get Close Properties</Text>
      </ImageBackground>  
      <Text>HomeScreenssssssssss</Text>
    </View>
  )
}

export default HomeScreen