import { View, Text, Image, Pressable } from 'react-native'
import React from 'react'
import styles from './styles'
import { useNavigation } from '@react-navigation/native';

const Property = ({property}) => {
  console.warn(property.id)
  const navigation = useNavigation()
  const goToProperty = () =>{
    navigation.navigate("Detailed Property", {propertyId:property.id});
  }

  return (
    <Pressable onPress={goToProperty} style={styles.container}>
      <Image style={styles.image}
        source={{uri:property.image}}/>
      <Text style={styles.bedrooms}>{property.bedroom}-Bedroom {property.title}</Text>
      <Text style={styles.description} numberOfLines={3} >
      {property.description}
      </Text>
      <Text style={styles.prices}>
          <Text style={styles.oldPrice}>${property.oldPrice} </Text>
          <Text style={styles.newPrice}> ${property.newPrice} </Text>
           Per Year
      </Text>
      <Text style={styles.totalPrice}>${property.totalPrice} Total</Text>
    </Pressable>
  )
};

export default Property