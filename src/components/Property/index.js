import { View, Text, Image } from 'react-native'
import React from 'react'
import styles from './styles'


const Property = () => {
  return (
    <View style={styles.container}>
      <Image style={styles.image}
        source={{uri:"https://i.ibb.co/TYTgYjc/realestate6.jpg"}}/>
      <Text style={styles.bedrooms}>1 6-Bedroom Maisonette</Text>
      <Text style={styles.description} numberOfLines={3} >
         Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
        when an unknown printer took a galley of type and scrambled it to make a type specimen book.</Text>
      <Text style={styles.prices}>
          <Text style={styles.oldPrice}>$52 </Text>
          <Text style={styles.newPrice}> $36 </Text>
           Per Year
      </Text>
      <Text style={styles.totalPrice}>$250 Total</Text>
    </View>
  )
};

export default Property